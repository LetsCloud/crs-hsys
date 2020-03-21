/**
 * 
 */
package io.crs.hsys.shared.dto.cube.hw;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class HwFroFocDto extends HwFroBaseDto {

	private String FCSTDATE;

	private Integer FOCSRC;

	private Long FOCNUM;

	private String EVHONA;

	private String EVHETF;

	private String EVHETT;

	private String SYSDAT;

	private Integer HTLNUM;

	private Integer RESSRC;

	private Long RESNUM;

	private String RESSTA;

	private String RESTIP;

	private String NEMZET;

	private String STAKOD;

	private String SCSKOD;

	private Integer ERTSRC;

	private Integer ERTNUM;

	private String ERTKOD;

	private String SOUKOD;

	private String PARKOD;

	private String PACKOD;

	private Integer OSZSZO;

	private Integer OSZAGY;

	private Integer OOOSZO;

	private Integer OOOAGY;

	private Integer DUPSZO;

	private Integer DUPAGY;

	private Integer ERKSZO;

	private Integer EJSSZO;

	private Integer UTASZO;

	private Integer KONSZO;

	private Integer CANCEL;

	private Integer ERKFEL;

	private Integer ERKTIN;

	private Integer ERKGYE;

	private Integer ERKBEB;

	private Integer EJSFEL;

	private Integer EJSTIN;

	private Integer EJSGYE;

	private Integer EJSBEB;

	private Integer UTAFEL;

	private Integer UTATIN;

	private Integer UTAGYE;

	private Integer UTABEB;

	private Double FORSZO;

	private Double FORSZA;

	private Double FORREG;

	private Double FORIFA;

	private Double FORVEN;

	private Double FORREN;

	private Double FORSPA;

	private Double FOREGY;

	private Double NFORSZO;

	private Double NFORSZA;

	private Double NFORREG;

	private Double NFORIFA;

	private Double NFORVEN;

	private Double NFORREN;

	private Double NFORSPA;

	private Double NFOREGY;

	public String getFCSTDATE() {
		return FCSTDATE;
	}

	@JsonSetter("FCSTDATE")
	public void setFCSTDATE(String fCSTDATE) {
		FCSTDATE = fCSTDATE;
	}

	public Integer getFOCSRC() {
		return FOCSRC;
	}

	@JsonSetter("FOCSRC")
	public void setFOCSRC(Integer fOCSRC) {
		FOCSRC = fOCSRC;
	}

	public Long getFOCNUM() {
		return FOCNUM;
	}

	@JsonSetter("FOCNUM")
	public void setFOCNUM(Long fOCNUM) {
		FOCNUM = fOCNUM;
	}

	public String getEVHONA() {
		return EVHONA;
	}

	@JsonSetter("EVHONA")
	public void setEVHONA(String eVHONA) {
		EVHONA = eVHONA;
	}

	public String getEVHETF() {
		return EVHETF;
	}

	@JsonSetter("EVHETF")
	public void setEVHETF(String eVHETF) {
		EVHETF = eVHETF;
	}

	public String getEVHETT() {
		return EVHETT;
	}

	@JsonSetter("EVHETT")
	public void setEVHETT(String eVHETT) {
		EVHETT = eVHETT;
	}

	public String getSYSDAT() {
		return SYSDAT;
	}

	@JsonSetter("SYSDAT")
	public void setSYSDAT(String sYSDAT) {
		SYSDAT = sYSDAT;
	}

	public Integer getHTLNUM() {
		return HTLNUM;
	}

	@JsonSetter("HTLNUM")
	public void setHTLNUM(Integer hTLNUM) {
		HTLNUM = hTLNUM;
	}

	public Integer getRESSRC() {
		return RESSRC;
	}

	@JsonSetter("RESSRC")
	public void setRESSRC(Integer rESSRC) {
		RESSRC = rESSRC;
	}

	public Long getRESNUM() {
		return RESNUM;
	}

	@JsonSetter("RESNUM")
	public void setRESNUM(Long rESNUM) {
		RESNUM = rESNUM;
	}

	public String getRESSTA() {
		return RESSTA;
	}

	@JsonSetter("RESSTA")
	public void setRESSTA(String rESSTA) {
		RESSTA = rESSTA;
	}

	public String getRESTIP() {
		return RESTIP;
	}

	@JsonSetter("RESTIP")
	public void setRESTIP(String rESTIP) {
		RESTIP = rESTIP;
	}

	public String getNEMZET() {
		return NEMZET;
	}

	@JsonSetter("NEMZET")
	public void setNEMZET(String nEMZET) {
		NEMZET = nEMZET;
	}

	public String getSTAKOD() {
		return STAKOD;
	}

	@JsonSetter("STAKOD")
	public void setSTAKOD(String sTAKOD) {
		STAKOD = sTAKOD;
	}

	public String getSCSKOD() {
		return SCSKOD;
	}

	@JsonSetter("SCSKOD")
	public void setSCSKOD(String sCSKOD) {
		SCSKOD = sCSKOD;
	}

	public Integer getERTSRC() {
		return ERTSRC;
	}

	@JsonSetter("ERTSRC")
	public void setERTSRC(Integer eRTSRC) {
		ERTSRC = eRTSRC;
	}

	public Integer getERTNUM() {
		return ERTNUM;
	}

	@JsonSetter("ERTNUM")
	public void setERTNUM(Integer eRTNUM) {
		ERTNUM = eRTNUM;
	}

	public String getERTKOD() {
		return ERTKOD;
	}

	@JsonSetter("ERTKOD")
	public void setERTKOD(String eRTKOD) {
		ERTKOD = eRTKOD;
	}

	public String getSOUKOD() {
		return SOUKOD;
	}

	@JsonSetter("SOUKOD")
	public void setSOUKOD(String sOUKOD) {
		SOUKOD = sOUKOD;
	}

	public String getPARKOD() {
		return PARKOD;
	}

	@JsonSetter("PARKOD")
	public void setPARKOD(String pARKOD) {
		PARKOD = pARKOD;
	}

	public String getPACKOD() {
		return PACKOD;
	}

	@JsonSetter("PACKOD")
	public void setPACKOD(String pACKOD) {
		PACKOD = pACKOD;
	}

	public Integer getOSZSZO() {
		return OSZSZO;
	}

	@JsonSetter("OSZSZO")
	public void setOSZSZO(Integer oSZSZO) {
		OSZSZO = oSZSZO;
	}

	public Integer getOSZAGY() {
		return OSZAGY;
	}

	@JsonSetter("OSZAGY")
	public void setOSZAGY(Integer oSZAGY) {
		OSZAGY = oSZAGY;
	}

	public Integer getOOOSZO() {
		return OOOSZO;
	}

	@JsonSetter("OOOSZO")
	public void setOOOSZO(Integer oOOSZO) {
		OOOSZO = oOOSZO;
	}

	public Integer getOOOAGY() {
		return OOOAGY;
	}

	@JsonSetter("OOOAGY")
	public void setOOOAGY(Integer oOOAGY) {
		OOOAGY = oOOAGY;
	}

	public Integer getDUPSZO() {
		return DUPSZO;
	}

	@JsonSetter("DUPSZO")
	public void setDUPSZO(Integer dUPSZO) {
		DUPSZO = dUPSZO;
	}

	public Integer getDUPAGY() {
		return DUPAGY;
	}

	@JsonSetter("DUPAGY")
	public void setDUPAGY(Integer dUPAGY) {
		DUPAGY = dUPAGY;
	}

	public Integer getERKSZO() {
		return ERKSZO;
	}

	@JsonSetter("ERKSZO")
	public void setERKSZO(Integer eRKSZO) {
		ERKSZO = eRKSZO;
	}

	public Integer getEJSSZO() {
		return EJSSZO;
	}

	@JsonSetter("EJSSZO")
	public void setEJSSZO(Integer eJSSZO) {
		EJSSZO = eJSSZO;
	}

	public Integer getUTASZO() {
		return UTASZO;
	}

	@JsonSetter("UTASZO")
	public void setUTASZO(Integer uTASZO) {
		UTASZO = uTASZO;
	}

	public Integer getKONSZO() {
		return KONSZO;
	}

	@JsonSetter("KONSZO")
	public void setKONSZO(Integer kONSZO) {
		KONSZO = kONSZO;
	}

	public Integer getCANCEL() {
		return CANCEL;
	}

	@JsonSetter("CANCEL")
	public void setCANCEL(Integer cANCEL) {
		CANCEL = cANCEL;
	}

	public Integer getERKFEL() {
		return ERKFEL;
	}

	@JsonSetter("ERKFEL")
	public void setERKFEL(Integer eRKFEL) {
		ERKFEL = eRKFEL;
	}

	public Integer getERKTIN() {
		return ERKTIN;
	}

	@JsonSetter("ERKTIN")
	public void setERKTIN(Integer eRKTIN) {
		ERKTIN = eRKTIN;
	}

	public Integer getERKGYE() {
		return ERKGYE;
	}

	@JsonSetter("ERKGYE")
	public void setERKGYE(Integer eRKGYE) {
		ERKGYE = eRKGYE;
	}

	public Integer getERKBEB() {
		return ERKBEB;
	}

	@JsonSetter("ERKBEB")
	public void setERKBEB(Integer eRKBEB) {
		ERKBEB = eRKBEB;
	}

	public Integer getEJSFEL() {
		return EJSFEL;
	}

	@JsonSetter("EJSFEL")
	public void setEJSFEL(Integer eJSFEL) {
		EJSFEL = eJSFEL;
	}

	public Integer getEJSTIN() {
		return EJSTIN;
	}

	@JsonSetter("EJSTIN")
	public void setEJSTIN(Integer eJSTIN) {
		EJSTIN = eJSTIN;
	}

	public Integer getEJSGYE() {
		return EJSGYE;
	}

	@JsonSetter("EJSGYE")
	public void setEJSGYE(Integer eJSGYE) {
		EJSGYE = eJSGYE;
	}

	public Integer getEJSBEB() {
		return EJSBEB;
	}

	@JsonSetter("EJSBEB")
	public void setEJSBEB(Integer eJSBEB) {
		EJSBEB = eJSBEB;
	}

	public Integer getUTAFEL() {
		return UTAFEL;
	}

	@JsonSetter("UTAFEL")
	public void setUTAFEL(Integer uTAFEL) {
		UTAFEL = uTAFEL;
	}

	public Integer getUTATIN() {
		return UTATIN;
	}

	@JsonSetter("UTATIN")
	public void setUTATIN(Integer uTATIN) {
		UTATIN = uTATIN;
	}

	public Integer getUTAGYE() {
		return UTAGYE;
	}

	@JsonSetter("UTAGYE")
	public void setUTAGYE(Integer uTAGYE) {
		UTAGYE = uTAGYE;
	}

	public Integer getUTABEB() {
		return UTABEB;
	}

	@JsonSetter("UTABEB")
	public void setUTABEB(Integer uTABEB) {
		UTABEB = uTABEB;
	}

	public Double getFORSZO() {
		return FORSZO;
	}

	@JsonSetter("FORSZO")
	public void setFORSZO(Double fORSZO) {
		FORSZO = fORSZO;
	}

	public Double getFORSZA() {
		return FORSZA;
	}

	@JsonSetter("FORSZA")
	public void setFORSZA(Double fORSZA) {
		FORSZA = fORSZA;
	}

	public Double getFORREG() {
		return FORREG;
	}

	@JsonSetter("FORREG")
	public void setFORREG(Double fORREG) {
		FORREG = fORREG;
	}

	public Double getFORIFA() {
		return FORIFA;
	}

	@JsonSetter("FORIFA")
	public void setFORIFA(Double fORIFA) {
		FORIFA = fORIFA;
	}

	public Double getFORVEN() {
		return FORVEN;
	}

	@JsonSetter("FORVEN")
	public void setFORVEN(Double fORVEN) {
		FORVEN = fORVEN;
	}

	public Double getFORREN() {
		return FORREN;
	}

	@JsonSetter("FORREN")
	public void setFORREN(Double fORREN) {
		FORREN = fORREN;
	}

	public Double getFORSPA() {
		return FORSPA;
	}

	@JsonSetter("FORSPA")
	public void setFORSPA(Double fORSPA) {
		FORSPA = fORSPA;
	}

	public Double getFOREGY() {
		return FOREGY;
	}

	@JsonSetter("FOREGY")
	public void setFOREGY(Double fOREGY) {
		FOREGY = fOREGY;
	}

	public Double getNFORSZO() {
		return NFORSZO;
	}

	@JsonSetter("NFORSZO")
	public void setNFORSZO(Double nFORSZO) {
		NFORSZO = nFORSZO;
	}

	public Double getNFORSZA() {
		return NFORSZA;
	}

	@JsonSetter("NFORSZA")
	public void setNFORSZA(Double nFORSZA) {
		NFORSZA = nFORSZA;
	}

	public Double getNFORREG() {
		return NFORREG;
	}

	@JsonSetter("NFORREG")
	public void setNFORREG(Double nFORREG) {
		NFORREG = nFORREG;
	}

	public Double getNFORIFA() {
		return NFORIFA;
	}

	@JsonSetter("NFORIFA")
	public void setNFORIFA(Double nFORIFA) {
		NFORIFA = nFORIFA;
	}

	public Double getNFORVEN() {
		return NFORVEN;
	}

	@JsonSetter("NFORVEN")
	public void setNFORVEN(Double nFORVEN) {
		NFORVEN = nFORVEN;
	}

	public Double getNFORREN() {
		return NFORREN;
	}

	@JsonSetter("NFORREN")
	public void setNFORREN(Double nFORREN) {
		NFORREN = nFORREN;
	}

	public Double getNFORSPA() {
		return NFORSPA;
	}

	@JsonSetter("NFORSPA")
	public void setNFORSPA(Double nFORSPA) {
		NFORSPA = nFORSPA;
	}

	public Double getNFOREGY() {
		return NFOREGY;
	}

	@JsonSetter("NFOREGY")
	public void setNFOREGY(Double nFOREGY) {
		NFOREGY = nFOREGY;
	}

	@Override
	public String toString() {
		return super.toString() + " FOCNUM=" + this.FOCNUM + " FOCSRC=" + this.FOCSRC + " RESSTA=" + this.RESSTA;
	}
}
