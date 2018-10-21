/**
 * 
 */
package io.crs.hsys.client.core.unauthorized;

import javax.inject.Inject;

import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

import io.crs.hsys.client.core.CoreNameTokens;

/**
 * @author CR
 *
 */
public class UnauthorizedPresenter extends Presenter<UnauthorizedPresenter.MyView, UnauthorizedPresenter.MyProxy> {
    interface MyView extends View {
        void setLinkToLogin(String link);
    }

    @ProxyStandard
    @NameToken(CoreNameTokens.UNAUTHORIZED)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<UnauthorizedPresenter> {
    }

    private final TokenFormatter tokenFormatter;

    @Inject
    UnauthorizedPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            TokenFormatter tokenFormatter) {
        super(eventBus, view, proxy, RevealType.RootLayout);

        this.tokenFormatter = tokenFormatter;
    }

    @Override
    protected void onReveal() {
        PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(CoreNameTokens.LOGIN)
                .with("redirectTo", History.getToken())
                .build();

        getView().setLinkToLogin(tokenFormatter.toPlaceToken(request));
    }
}