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
    public void setDisplay(IDisplay display) {
        for (IPresenter presenter:presenters) {
            presenter.setDisplay(display);
        }
    }

    @Override
    public void setInformation(String textName) {
        for (IPresenter presenter:presenters) {
            presenter.setText(textName);
        }
    }

    @Override
    public void setOptions() {
        for (IPresenter presenter:presenters) {
            presenter.setupOptions();
        }
    }

    @Override
    public void setError(String errorName) {
        for (IPresenter presenter:presenters) {
            presenter.setError(errorName);
        }
    }

    @Override
    public void setTextPlain(String text) {
        for (IPresenter presenter:presenters) {
            presenter.setTextPlain(text);
        }
    }

    @Override
    public void showView() {
        for (IPresenter presenter:presenters) {
            presenter.show();
        }
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
