/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.crs.hsys.server.entity.chat.Chat;
import io.crs.hsys.server.entity.chat.ChatPost;
import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.common.FcmToken;
import io.crs.hsys.server.model.FcmMessage;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.ChatRepository;
import io.crs.hsys.server.service.ChatService;
import io.crs.hsys.server.service.FcmService;
import io.crs.hsys.shared.dto.chat.NotificationDto;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public class ChatServiceImpl extends CrudServiceImpl<Chat, ChatRepository> implements ChatService {
	private static final Logger logger = Logger.getLogger(ChatServiceImpl.class.getName());

	private final ChatRepository repository;
	private final AccountRepository accountRepository;
	private final FcmService fcmService;
	private final ObjectMapper objectMapper;

	public ChatServiceImpl(ChatRepository repository, AccountRepository accountRepository, FcmService fcmService,
			ObjectMapper objectMapper) {
		super(repository);
		this.repository = repository;
		this.accountRepository = accountRepository;
		this.fcmService = fcmService;
		this.objectMapper = objectMapper;
	}

	@Override
	public List<Chat> getAll(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);

		if (account == null)
			return null;

		return repository.getByAccount(account);
	}

	@Override
	public Chat create(Chat dto) throws Throwable {
		Date updated = new Date();
		dto.setCreated(updated);
		dto.setUpdated(updated);
		Chat entiy = super.create(dto);
		notifyReceivers(entiy.getSender(), entiy, false);
		return entiy;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findByWebSafeKey(accountWebSafeKey));
		return parents;
	}

	@Override
	public Chat addPost(String chatKey, ChatPost post) throws EntityValidationException, UniqueIndexConflictException {
		logger.info("addPost()->post=" + post);
		Date updated = new Date();
		Chat chat = repository.findByWebSafeKey(chatKey);
		post.setCreated(updated);
		chat.getPosts().add(post);
		chat.setUpdated(updated);
		chat = repository.save(chat);
		notifyReceivers(post.getSender(), chat, true);
		return chat;
	}

	public void notifyReceivers(AppUser sender, Chat chat, Boolean isAddPost) {
		logger.info("sender=" + sender);
		logger.info("chat=" + chat);

		NotificationDto notification = new NotificationDto(sender.getName(),
				chat.getPosts().get(chat.getPosts().size() - 1).getMessage(), sender.getPicture(),
				chat.getUrl() + chat.getWebSafeKey());

		logger.info("notification.getAction()=" + notification.getClick_action());

		List<String> tokens = new ArrayList<String>();

		// Sima postolás esetén a chatet inditó tokenjét is begyűjtjük
		if (isAddPost) {
			logger.info("chat.getSender().getName()=" + chat.getSender().getName());
			for (FcmToken chatStarterToken : chat.getSender().getFcmTokens()) {
				if (!tokens.contains(chatStarterToken.getToken()))
					tokens.add(chatStarterToken.getToken());
			}
		}

		// Majd az értesítettek tokenjeit is begyűjtjük
		for (AppUser receiver : chat.getReceivers()) {
			logger.info("receiver.getName()=" + receiver.getName());
			for (FcmToken receiverToken : receiver.getFcmTokens()) {
				if (!tokens.contains(receiverToken.getToken()))
					tokens.add(receiverToken.getToken());
			}
		}

		// Végül kivesszük a sender token-ét
		logger.info("sender.getName()=" + sender.getName());
		for (FcmToken senderToken : sender.getFcmTokens())
			tokens.remove(senderToken.getToken());

		for (String receiverToken : tokens) {
			logger.info("receiverToken=" + receiverToken);
//			FcmService2.send_FCM_Notification(receiverToken, notification);
			FcmMessage message = new FcmMessage(notification, receiverToken);
			try {
				fcmService.postMessage(objectMapper.writeValueAsString(message));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
