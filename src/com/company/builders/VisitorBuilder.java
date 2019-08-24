package com.company.builders;

import com.company.people.Visitor;

import java.util.List;

public class VisitorBuilder {

    public void build(List<Visitor> visitorList, int upperBorder) {
        for (int i = 0; i < upperBorder; i++) {
            boolean pass = ((int) (Math.random() * 2)) == 1;
            Visitor visitor = new Visitor();
            visitor.setPass(pass);
            visitorList.add(visitor);
        }
    }
}
