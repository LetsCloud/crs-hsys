/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import io.crs.hsys.server.entity.chat.Chat;
import io.crs.hsys.server.entity.chat.ChatPost;
import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.common.FcmToken;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.ChatRepository;
import io.crs.hsys.server.service.ChatService;
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

	public ChatServiceImpl(ChatRepository repository, AccountRepository accountRepository) {
		super(repository);
		this.repository = repository;
		this.accountRepository = accountRepository;
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
		Date updated = new Date();
		Chat chat = repository.findByWebSafeKey(chatKey);
		post.setCreated(updated);
		chat.getPosts().add(post);
		chat.setUpdated(updated);
		chat = repository.save(chat);
		notifyReceivers(chat.getSender(), chat, true);
		return chat;
	}

	public void notifyReceivers(AppUser sender, Chat chat, Boolean isAddPost) {
		logger.info("notifyReceivers()");

		NotificationDto nd = new NotificationDto(sender.getName(),
				chat.getPosts().get(chat.getPosts().size() - 1).getMessage(), sender.getPicture(),
				chat.getUrl() + chat.getWebSafeKey());

		List<String> tokens = new ArrayList<String>();

		// Sima postolás esetén a chatet inditó tokenjét is begyűjtjük
		if (isAddPost) {
		logger.info("notifyReceivers()->chatStarter=" + chat.getSender().getName());
			for (FcmToken chatStarterToken : chat.getSender().getFcmTokens()) {
				if (!tokens.contains(chatStarterToken.getToken()))
					tokens.add(chatStarterToken.getToken());
			}
		}

		// Majd az értesítettek tokenjeit is begyűjtjük
		for (AppUser receiver : chat.getReceivers()) {
			logger.info("notifyReceivers()->receiver=" + receiver.getName());
			for (FcmToken receiverToken : receiver.getFcmTokens()) {
				if (!tokens.contains(receiverToken.getToken()))
					tokens.add(receiverToken.getToken());
			}
		}

		// Végül kivesszük a sender token-ét
		logger.info("notifyReceivers()->sender=" + sender.getName());
		for (FcmToken senderToken : sender.getFcmTokens())
			tokens.remove(senderToken.getToken());

		for (String token : tokens) {
			logger.info("notifyReceivers()->token=" + token);
			FcmService2.send_FCM_Notification(token, nd);
		}
	}

}
