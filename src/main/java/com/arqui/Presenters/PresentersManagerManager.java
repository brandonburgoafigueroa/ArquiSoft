package com.arqui.Presenters;

import com.arqui.Interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class PresentersManager implements IPresenters {

    private List<IPresenter> presenters;

    public PresentersManager() {
        presenters=new ArrayList<>();
    }

    @Override
    public void addPresenter(IPresenter presenter) {
        presenters.add(presenter);
    }

    @Override
    public void setModelView(IModelView modelView) {
        for (IPresenter presenter:presenters) {
            presenter.setModelView(modelView);
            presenter.show();
        }
    }

    @Override
    public void setPersistenceType(IResponse response) {
        for (IPresenter presenter:presenters) {
            presenter.setPersistenceType(response);
        }
    }

    @Override
    public void setError(IResponse error) {
        for (IPresenter presenter:presenters) {
            presenter.setError(error);
            presenter.show();
        }
    }
}
