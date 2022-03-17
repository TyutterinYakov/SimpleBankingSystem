package banking.api.model;

import banking.store.entity.BillType;

public class BillModel {

    private String name;
    private BillType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }
}
