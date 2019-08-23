package com.company.builders;

import com.company.constants.Constants;
import com.company.people.Visitor;

import java.util.List;

public class VisitorBuilder {

    public void build(List<Visitor> visitorList) {
        for (int i = 0; i < Constants.VISITORS_AMOUNT.getValue(); i++) {
            boolean pass = ((int) (Math.random() * 2)) == 0;
            Visitor visitor = new Visitor();
            visitor.setPass(pass);
            visitorList.add(visitor);
        }
    }
}
