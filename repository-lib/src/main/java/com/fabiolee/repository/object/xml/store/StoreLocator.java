package com.fabiolee.repository.object.xml.store;

import com.fabiolee.repository.object.xml.BaseObject;
import com.fabiolee.repository.util.Constant;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = Constant.Key.LOCATOR, strict = false)
public class StoreLocator extends BaseObject {
    @Element(name = Constant.Key.JOHOR)
    private StoreJohor johor;

    @Element(name = Constant.Key.KEDAH)
    private StoreKedah kedah;

    @Element(name = Constant.Key.KELANTAN)
    private StoreKelantan kelantan;

    @Element(name = Constant.Key.KL)
    private StoreKl kl;

    @Element(name = Constant.Key.MELAKA)
    private StoreMelaka melaka;

    @Element(name = Constant.Key.NS)
    private StoreNs ns;

    @Element(name = Constant.Key.PAHANG)
    private StorePahang pahang;

    @Element(name = Constant.Key.PENANG)
    private StorePenang penang;

    @Element(name = Constant.Key.PERAK)
    private StorePerak perak;

    @Element(name = Constant.Key.SABAH)
    private StoreSabah sabah;

    @Element(name = Constant.Key.SARAWAK)
    private StoreSarawak sarawak;

    @Element(name = Constant.Key.SELANGOR)
    private StoreSelangor selangor;

    @Element(name = Constant.Key.TERENGGANU)
    private StoreTerengganu terengganu;

    public StoreJohor getJohor() {
        return johor;
    }

    public void setJohor(StoreJohor johor) {
        this.johor = johor;
    }

    public StoreKedah getKedah() {
        return kedah;
    }

    public void setKedah(StoreKedah kedah) {
        this.kedah = kedah;
    }

    public StoreKelantan getKelantan() {
        return kelantan;
    }

    public void setKelantan(StoreKelantan kelantan) {
        this.kelantan = kelantan;
    }

    public StoreKl getKl() {
        return kl;
    }

    public void setKl(StoreKl kl) {
        this.kl = kl;
    }

    public StoreMelaka getMelaka() {
        return melaka;
    }

    public void setMelaka(StoreMelaka melaka) {
        this.melaka = melaka;
    }

    public StoreNs getNs() {
        return ns;
    }

    public void setNs(StoreNs ns) {
        this.ns = ns;
    }

    public StorePahang getPahang() {
        return pahang;
    }

    public void setPahang(StorePahang pahang) {
        this.pahang = pahang;
    }

    public StorePenang getPenang() {
        return penang;
    }

    public void setPenang(StorePenang penang) {
        this.penang = penang;
    }

    public StorePerak getPerak() {
        return perak;
    }

    public void setPerak(StorePerak perak) {
        this.perak = perak;
    }

    public StoreSabah getSabah() {
        return sabah;
    }

    public void setSabah(StoreSabah sabah) {
        this.sabah = sabah;
    }

    public StoreSarawak getSarawak() {
        return sarawak;
    }

    public void setSarawak(StoreSarawak sarawak) {
        this.sarawak = sarawak;
    }

    public StoreSelangor getSelangor() {
        return selangor;
    }

    public void setSelangor(StoreSelangor selangor) {
        this.selangor = selangor;
    }

    public StoreTerengganu getTerengganu() {
        return terengganu;
    }

    public void setTerengganu(StoreTerengganu terengganu) {
        this.terengganu = terengganu;
    }
}
