package com.fabiolee.repository.object.xml.reward;

import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Constant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = Constant.Key.PARTNER, strict = false)
public class PrivilegePartner extends BaseObject {
    @ElementList(name = Constant.Key.MERCHANT, inline = true)
    private List<PrivilegeMerchant> merchant;

    public List<PrivilegeMerchant> getMerchant() {
        return merchant;
    }

    public void setMerchant(List<PrivilegeMerchant> merchant) {
        this.merchant = merchant;
    }
}
