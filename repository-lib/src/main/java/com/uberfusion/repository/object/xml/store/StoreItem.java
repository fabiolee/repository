package com.uberfusion.repository.object.xml.store;

import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.util.Constant;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = Constant.Key.ITEM, strict = false)
public class StoreItem extends BaseObject {
    @Element(name = Constant.Key.LOCATION)
    private String location;

    @Element(name = Constant.Key.ADDRESS)
    private String address;

    @Element(name = Constant.Key.OPERATINGHOUR)
    private String operatingHour;

    @Element(name = Constant.Key.SHOPTYPE, required = false)
    private String shopType;

    @Element(name = Constant.Key.LATITUDE, required = false)
    private String latitude;

    @Element(name = Constant.Key.LONGITUDE, required = false)
    private String longitude;

    @Element(name = Constant.Key.IPHONE)
    private boolean iPhone;

    @Element(name = Constant.Key.SMARTPHONES)
    private boolean smartphones;

    @Element(name = Constant.Key.BROADBAND)
    private boolean broadband;

    @Element(name = Constant.Key.TWOFOURHOURPAYMENTKIOSK)
    private boolean twoFourHourPaymentKiosk;

    @Element(name = Constant.Key.SIMREPLACEMENT)
    private boolean simReplacement;

    @Element(name = Constant.Key.BILLPAYMENT)
    private boolean billPayment;

    @Element(name = Constant.Key.LINEACTIVATION)
    private boolean lineActivation;

    @Element(name = Constant.Key.TRANSFEROWNERSHIP)
    private boolean transferOwnership;

    @Element(name = Constant.Key.MNP)
    private boolean mnp;

    @Element(name = Constant.Key.LIVEDEMOPHONES, required = false)
    private boolean liveDemoPhones;

    @Element(name = Constant.Key.CREDITCARDEASYPAYMENT)
    private boolean creditcardEasyPayment;

    @Element(name = Constant.Key.BUSINESSSMESIGNUPS)
    private boolean businessSMEsignups;

    @Element(name = Constant.Key.CHANGEUPGRADEPLANS)
    private boolean changeUpgradePlans;

    @Element(name = Constant.Key.ACCOUNTTERMINATION)
    private boolean accountTermination;

    @Element(name = Constant.Key.PHONEWARRANTYSERVICE)
    private boolean phoneWarrantyService;

    @Element(name = Constant.Key.SIMPLEMASTERCARD)
    private boolean simpleMasterCard;

    @Element(name = Constant.Key.ACCESSORIES, required = false)
    private boolean accessories;

    @Element(name = Constant.Key.PHONEINSURANCE, required = false)
    private boolean phoneInsurance;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatingHour() {
        return operatingHour;
    }

    public void setOperatingHour(String operatingHour) {
        this.operatingHour = operatingHour;
    }

    public boolean isiPhone() {
        return iPhone;
    }

    public void setiPhone(boolean iPhone) {
        this.iPhone = iPhone;
    }

    public boolean isSmartphones() {
        return smartphones;
    }

    public void setSmartphones(boolean smartphones) {
        this.smartphones = smartphones;
    }

    public boolean isBroadband() {
        return broadband;
    }

    public void setBroadband(boolean broadband) {
        this.broadband = broadband;
    }

    public boolean isTwoFourHourPaymentKiosk() {
        return twoFourHourPaymentKiosk;
    }

    public void setTwoFourHourPaymentKiosk(boolean twoFourHourPaymentKiosk) {
        this.twoFourHourPaymentKiosk = twoFourHourPaymentKiosk;
    }

    public boolean isSimReplacement() {
        return simReplacement;
    }

    public void setSimReplacement(boolean simReplacement) {
        this.simReplacement = simReplacement;
    }

    public boolean isBillPayment() {
        return billPayment;
    }

    public void setBillPayment(boolean billPayment) {
        this.billPayment = billPayment;
    }

    public boolean isLineActivation() {
        return lineActivation;
    }

    public void setLineActivation(boolean lineActivation) {
        this.lineActivation = lineActivation;
    }

    public boolean isTransferOwnership() {
        return transferOwnership;
    }

    public void setTransferOwnership(boolean transferOwnership) {
        this.transferOwnership = transferOwnership;
    }

    public boolean isMnp() {
        return mnp;
    }

    public void setMnp(boolean mnp) {
        this.mnp = mnp;
    }

    public boolean isCreditcardEasyPayment() {
        return creditcardEasyPayment;
    }

    public void setCreditcardEasyPayment(boolean creditcardEasyPayment) {
        this.creditcardEasyPayment = creditcardEasyPayment;
    }

    public boolean isBusinessSMEsignups() {
        return businessSMEsignups;
    }

    public void setBusinessSMEsignups(boolean businessSMEsignups) {
        this.businessSMEsignups = businessSMEsignups;
    }

    public boolean isChangeUpgradePlans() {
        return changeUpgradePlans;
    }

    public void setChangeUpgradePlans(boolean changeUpgradePlans) {
        this.changeUpgradePlans = changeUpgradePlans;
    }

    public boolean isAccountTermination() {
        return accountTermination;
    }

    public void setAccountTermination(boolean accountTermination) {
        this.accountTermination = accountTermination;
    }

    public boolean isPhoneWarrantyService() {
        return phoneWarrantyService;
    }

    public void setPhoneWarrantyService(boolean phoneWarrantyService) {
        this.phoneWarrantyService = phoneWarrantyService;
    }

    public boolean isSimpleMasterCard() {
        return simpleMasterCard;
    }

    public void setSimpleMasterCard(boolean simpleMasterCard) {
        this.simpleMasterCard = simpleMasterCard;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isLiveDemoPhones() {
        return liveDemoPhones;
    }

    public void setLiveDemoPhones(boolean liveDemoPhones) {
        this.liveDemoPhones = liveDemoPhones;
    }

    public boolean isAccessories() {
        return accessories;
    }

    public void setAccessories(boolean accessories) {
        this.accessories = accessories;
    }

    public boolean isPhoneInsurance() {
        return phoneInsurance;
    }

    public void setPhoneInsurance(boolean phoneInsurance) {
        this.phoneInsurance = phoneInsurance;
    }
}
