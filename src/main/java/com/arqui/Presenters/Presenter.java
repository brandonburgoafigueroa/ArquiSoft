package com.arqui.Presenters;

import com.arqui.Interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements IPresenter {
private List<IView> Views;
public Presenter()
{
    Views=new ArrayList<>();
}
    @Override
    public void addView(IView view) {
        Views.add(view);
    }

    @Override
    public void show() {
        for (IView view:Views) {
            view.showView();
        }
    }

    @Override
    public void setModelView(IModelView modelView) {
        ArrayList<String> options=modelView.getOptions();
        String information=modelView.getInformation();
        for (IView view:Views) {
            setupView(options, information, view);
        }
    }

    @Override
    public void setError(IResponse error) {
        String errorText=error.getContentResponse();
        for (IView view:Views) {
            view.setInformation(errorText);
        }
    }

    @Override
    public void setPersistenceType(IResponse response) {
    String Type=response.getContentResponse();
        for (IView view:Views) {
            view.setPersistenceText(Type);
        }
    }

    private void setupView(ArrayList<String> options, String information, IView view) {
        for (String option:options) {
            view.setOption(option);
        }
        view.setInformation(information);
    }

}
