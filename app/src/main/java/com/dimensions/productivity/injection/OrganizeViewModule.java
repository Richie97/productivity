package com.dimensions.productivity.injection;

import android.support.annotation.NonNull;

import com.dimensions.productivity.interactor.OrganizeInteractor;
import com.dimensions.productivity.interactor.impl.OrganizeInteractorImpl;
import com.dimensions.productivity.presenter.loader.PresenterFactory;
import com.dimensions.productivity.presenter.OrganizePresenter;
import com.dimensions.productivity.presenter.impl.OrganizePresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class OrganizeViewModule 
{
	@Provides
	public OrganizeInteractor provideInteractor()
	{
		return new OrganizeInteractorImpl();
	}

	@Provides
	public PresenterFactory<OrganizePresenter> providePresenterFactory(@NonNull final OrganizeInteractor interactor)
	{
		return new PresenterFactory<OrganizePresenter>()
        {
            @NonNull
            @Override
            public OrganizePresenter create()
            {
                return new OrganizePresenterImpl(interactor);
            }
        };
	}
}
