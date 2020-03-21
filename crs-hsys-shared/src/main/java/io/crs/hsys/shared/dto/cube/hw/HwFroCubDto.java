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
public class HwFroCubDto extends HwFroBaseDto {

	private Integer CUBSRC;

	private Integer CUBNUM;

	private String EVHONA;

	private String EVHETF;

	private Integer HETNAP;

	private String RESDAT;

	private String RESALK;

	private String FELDAT;

	private Integer FELHETNAP;

	private String TORDAT;

	private Integer TORHETNAP;

	private Integer HTLNUM;

	private Integer RESSRC;

	private Integer RESNUM;

	private Integer TORSRC;

	private Integer TORNUM;

	private String RESTIP;

	private String NEMZET;

	private String SCSKOD;

	private String STAKOD;

	private String ERTKOD;

	private Integer ERTSRC;

	private Integer ERTNUM;

	private String SOUKOD;

	private String TIPKOD;

	private String FONNEV;

	private Integer FONSRC;

	private Integer FONKOD;

	private String PARKOD;

	private String RESVAR;

	private String SZOTIP;

	private String EMELET;

	private String SZONUM;

	private String PACKOD;

	private String LAKVAR;

	private Integer VENKOR;

	private Double PACOSZ;

	private String PACVAL;

	private Integer OSZSZO;

	private Integer OSZAGY;

	private Integer OOOSZO;

	private Integer OOOAGY;

	private Integer ERKSZO;

	private Integer EJSSZO;

	private Integer DUPSZO;

	private Integer DUPAGY;

	private Integer UTASZO;

	private Integer WALKIN;

	private Integer COMSZO;

	private Integer DAYUSE;

	private Integer CANCEL;

	private Integer NOSHOW;

	private Integer KI1SZO;

	private Integer KI2SZO;

	private Integer KITSZO;

	private Integer KONSZO;

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

	private Integer VIPVEN;

	private Double TERSZO;

	private Double TERSZA;

	private Double TERIFA;

	private Double TERREG;

	private Double TERVEN;

	private Double TERREN;

	private Double TERSPA;

	private Double TERFIT;

	private Double TEREGY;

	private Double TERELO;

	private Double TERELS;

	private Double TEROSZ;

	private Double ENGSZO;

	private Double ENGSZA;

	private Double ENGIFA;

	private Double ENGREG;

	private Double ENGVEN;

	private Double ENGREN;

	private Double ENGSPA;

	private Double ENGFIT;

	private Double ENGEGY;

	private Double ENGELO;

	private Double ENGELS;

	private Double ENGOSZ;

	private Double TENSZO;

	private Double TENSZA;

	private Double TENIFA;

	private Double TENREG;

	private Double TENVEN;

	private Double TENREN;

	private Double TENSPA;

	private Double TENFIT;

	private Double TENEGY;

	private Double TENELO;

	private Double TENELS;

	private Double TENOSZ;

	private Double ENNSZO;

	private Double ENNSZA;

	private Double ENNIFA;

	private Double ENNREG;

	private Double ENNVEN;

	private Double ENNREN;

	private Double ENNSPA;

	private Double ENNFIT;

	private Double ENNEGY;

	private Double ENNELO;

	private Double ENNELS;

	private Double ENNOSZ;

	private Double FIZ_KP;

	private Double FIZ_HK;

	private Double FIZ_FS;

	private Double FIZ_BS;

	private Double VENFOR;

	private Integer C_GENID;

	private Integer C_SOURCE;

	private Integer C_TARGET;

	private Integer C_CONTROL;

	public Integer getCUBSRC() {
		return CUBSRC;
	}

	@JsonSetter("CUBSRC")
	public void setCUBSRC(Integer cUBSRC) {
		CUBSRC = cUBSRC;
	}

	public Integer getCUBNUM() {
		return CUBNUM;
	}

	@JsonSetter("CUBNUM")
	public void setCUBNUM(Integer cUBNUM) {
		CUBNUM = cUBNUM;
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

	public Integer getHETNAP() {
		return HETNAP;
	}

	@JsonSetter("HETNAP")
	public void setHETNAP(Integer hETNAP) {
		HETNAP = hETNAP;
	}

	public String getRESDAT() {
		return RESDAT;
	}

	@JsonSetter("RESDAT")
	public void setRESDAT(String rESDAT) {
		RESDAT = rESDAT;
	}

	public String getRESALK() {
		return RESALK;
	}

	@JsonSetter("RESALK")
	public void setRESALK(String rESALK) {
		RESALK = rESALK;
	}

	public String getFELDAT() {
		return FELDAT;
	}

	@JsonSetter("FELDAT")
	public void setFELDAT(String fELDAT) {
		FELDAT = fELDAT;
	}

	public Integer getFELHETNAP() {
		return FELHETNAP;
	}

	@JsonSetter("FELHETNAP")
	public void setFELHETNAP(Integer fELHETNAP) {
		FELHETNAP = fELHETNAP;
	}

	public String getTORDAT() {
		return TORDAT;
	}

	@JsonSetter("TORDAT")
	public void setTORDAT(String tORDAT) {
		TORDAT = tORDAT;
	}

	public Integer getTORHETNAP() {
		return TORHETNAP;
	}

	@JsonSetter("TORHETNAP")
	public void setTORHETNAP(Integer tORHETNAP) {
		TORHETNAP = tORHETNAP;
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

	public Integer getRESNUM() {
		return RESNUM;
	}

	@JsonSetter("RESNUM")
	public void setRESNUM(Integer rESNUM) {
		RESNUM = rESNUM;
	}

	public Integer getTORSRC() {
		return TORSRC;
	}

	@JsonSetter("TORSRC")
	public void setTORSRC(Integer tORSRC) {
		TORSRC = tORSRC;
	}

	public Integer getTORNUM() {
		return TORNUM;
	}

	@JsonSetter("TORNUM")
	public void setTORNUM(Integer tORNUM) {
		TORNUM = tORNUM;
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

	public String getSCSKOD() {
		return SCSKOD;
	}

	@JsonSetter("SCSKOD")
	public void setSCSKOD(String sCSKOD) {
		SCSKOD = sCSKOD;
	}

	public String getSTAKOD() {
		return STAKOD;
	}

	@JsonSetter("STAKOD")
	public void setSTAKOD(String sTAKOD) {
		STAKOD = sTAKOD;
	}

	public String getERTKOD() {
		return ERTKOD;
	}

	@JsonSetter("ERTKOD")
	public void setERTKOD(String eRTKOD) {
		ERTKOD = eRTKOD;
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

	public String getSOUKOD() {
		return SOUKOD;
	}

	@JsonSetter("SOUKOD")
	public void setSOUKOD(String sOUKOD) {
		SOUKOD = sOUKOD;
	}

	public String getTIPKOD() {
		return TIPKOD;
	}

	@JsonSetter("TIPKOD")
	public void setTIPKOD(String tIPKOD) {
		TIPKOD = tIPKOD;
	}

	public String getFONNEV() {
		return FONNEV;
	}

	@JsonSetter("FONNEV")
	public void setFONNEV(String fONNEV) {
		FONNEV = fONNEV;
	}

	public Integer getFONSRC() {
		return FONSRC;
	}

	@JsonSetter("FONSRC")
	public void setFONSRC(Integer fONSRC) {
		FONSRC = fONSRC;
	}

	public Integer getFONKOD() {
		return FONKOD;
	}

	@JsonSetter("FONKOD")
	public void setFONKOD(Integer fONKOD) {
		FONKOD = fONKOD;
	}

	public String getPARKOD() {
		return PARKOD;
	}

	@JsonSetter("PARKOD")
	public void setPARKOD(String pARKOD) {
		PARKOD = pARKOD;
	}

	public String getRESVAR() {
		return RESVAR;
	}

	@JsonSetter("RESVAR")
	public void setRESVAR(String rESVAR) {
		RESVAR = rESVAR;
	}

	public String getEMELET() {
		return EMELET;
	}

	@JsonSetter("EMELET")
	public void setEMELET(String eMELET) {
		EMELET = eMELET;
	}

	public String getSZONUM() {
		return SZONUM;
	}

	@JsonSetter("SZONUM")
	public void setSZONUM(String sZONUM) {
		SZONUM = sZONUM;
	}

	public String getPACKOD() {
		return PACKOD;
	}

	@JsonSetter("PACKOD")
	public void setPACKOD(String pACKOD) {
		PACKOD = pACKOD;
	}

	public String getLAKVAR() {
		return LAKVAR;
	}

	@JsonSetter("LAKVAR")
	public void setLAKVAR(String lAKVAR) {
		LAKVAR = lAKVAR;
	}

	public Integer getVENKOR() {
		return VENKOR;
	}

	@JsonSetter("VENKOR")
	public void setVENKOR(Integer vENKOR) {
		VENKOR = vENKOR;
	}

	public Double getPACOSZ() {
		return PACOSZ;
	}

	@JsonSetter("PACOSZ")
	public void setPACOSZ(Double pACOSZ) {
		PACOSZ = pACOSZ;
	}

	public String getPACVAL() {
		return PACVAL;
	}

	@JsonSetter("PACVAL")
	public void setPACVAL(String pACVAL) {
		PACVAL = pACVAL;
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

	public Integer getUTASZO() {
		return UTASZO;
	}

	@JsonSetter("UTASZO")
	public void setUTASZO(Integer uTASZO) {
		UTASZO = uTASZO;
	}

	public Integer getWALKIN() {
		return WALKIN;
	}

	@JsonSetter("WALKIN")
	public void setWALKIN(Integer wALKIN) {
		WALKIN = wALKIN;
	}

	public Integer getCOMSZO() {
		return COMSZO;
	}

	@JsonSetter("COMSZO")
	public void setCOMSZO(Integer cOMSZO) {
		COMSZO = cOMSZO;
	}

	public Integer getDAYUSE() {
		return DAYUSE;
	}

	@JsonSetter("DAYUSE")
	public void setDAYUSE(Integer dAYUSE) {
		DAYUSE = dAYUSE;
	}

	public Integer getCANCEL() {
		return CANCEL;
	}

	@JsonSetter("CANCEL")
	public void setCANCEL(Integer cANCEL) {
		CANCEL = cANCEL;
	}

	public Integer getNOSHOW() {
		return NOSHOW;
	}

	@JsonSetter("NOSHOW")
	public void setNOSHOW(Integer nOSHOW) {
		NOSHOW = nOSHOW;
	}

	public Integer getKI1SZO() {
		return KI1SZO;
	}

	@JsonSetter("KI1SZO")
	public void setKI1SZO(Integer kI1SZO) {
		KI1SZO = kI1SZO;
	}

	public Integer getKI2SZO() {
		return KI2SZO;
	}

	@JsonSetter("KI2SZO")
	public void setKI2SZO(Integer kI2SZO) {
		KI2SZO = kI2SZO;
	}

	public Integer getKITSZO() {
		return KITSZO;
	}

	@JsonSetter("KITSZO")
	public void setKITSZO(Integer kITSZO) {
		KITSZO = kITSZO;
	}

	public Integer getKONSZO() {
		return KONSZO;
	}

	@JsonSetter("KONSZO")
	public void setKONSZO(Integer kONSZO) {
		KONSZO = kONSZO;
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

	public Integer getVIPVEN() {
		return VIPVEN;
	}

	@JsonSetter("VIPVEN")
	public void setVIPVEN(Integer vIPVEN) {
		VIPVEN = vIPVEN;
	}

	public Double getTERSZO() {
		return TERSZO;
	}

	@JsonSetter("TERSZO")
	public void setTERSZO(Double tERSZO) {
		TERSZO = tERSZO;
	}

	public Double getTERSZA() {
		return TERSZA;
	}

	@JsonSetter("TERSZA")
	public void setTERSZA(Double tERSZA) {
		TERSZA = tERSZA;
	}

	public Double getTERIFA() {
		return TERIFA;
	}

	@JsonSetter("TERIFA")
	public void setTERIFA(Double tERIFA) {
		TERIFA = tERIFA;
	}

	public Double getTERVEN() {
		return TERVEN;
	}

	@JsonSetter("TERVEN")
	public void setTERVEN(Double tERVEN) {
		TERVEN = tERVEN;
	}

	public Double getTERREN() {
		return TERREN;
	}

	@JsonSetter("TERREN")
	public void setTERREN(Double tERREN) {
		TERREN = tERREN;
	}

	public Double getTERSPA() {
		return TERSPA;
	}

	@JsonSetter("TERSPA")
	public void setTERSPA(Double tERSPA) {
		TERSPA = tERSPA;
	}

	public Double getTEREGY() {
		return TEREGY;
	}

	@JsonSetter("TEREGY")
	public void setTEREGY(Double tEREGY) {
		TEREGY = tEREGY;
	}

	public Double getTERELO() {
		return TERELO;
	}

	@JsonSetter("TERELO")
	public void setTERELO(Double tERELO) {
		TERELO = tERELO;
	}

	public Double getTERELS() {
		return TERELS;
	}

	@JsonSetter("TERELS")
	public void setTERELS(Double tERELS) {
		TERELS = tERELS;
	}

	public Double getTEROSZ() {
		return TEROSZ;
	}

	@JsonSetter("TEROSZ")
	public void setTEROSZ(Double tEROSZ) {
		TEROSZ = tEROSZ;
	}

	public Double getENGSZO() {
		return ENGSZO;
	}

	@JsonSetter("ENGSZO")
	public void setENGSZO(Double eNGSZO) {
		ENGSZO = eNGSZO;
	}

	public Double getENGSZA() {
		return ENGSZA;
	}

	@JsonSetter("ENGSZA")
	public void setENGSZA(Double eNGSZA) {
		ENGSZA = eNGSZA;
	}

	public Double getENGVEN() {
		return ENGVEN;
	}

	@JsonSetter("ENGVEN")
	public void setENGVEN(Double eNGVEN) {
		ENGVEN = eNGVEN;
	}

	public Double getENGIFA() {
		return ENGIFA;
	}

	@JsonSetter("ENGIFA")
	public void setENGIFA(Double eNGIFA) {
		ENGIFA = eNGIFA;
	}

	public Double getENGREN() {
		return ENGREN;
	}

	@JsonSetter("ENGREN")
	public void setENGREN(Double eNGREN) {
		ENGREN = eNGREN;
	}

	public Double getENGSPA() {
		return ENGSPA;
	}

	@JsonSetter("ENGSPA")
	public void setENGSPA(Double eNGSPA) {
		ENGSPA = eNGSPA;
	}

	public Double getENGEGY() {
		return ENGEGY;
	}

	@JsonSetter("ENGEGY")
	public void setENGEGY(Double eNGEGY) {
		ENGEGY = eNGEGY;
	}

	public Double getENGELO() {
		return ENGELO;
	}

	@JsonSetter("ENGELO")
	public void setENGELO(Double eNGELO) {
		ENGELO = eNGELO;
	}

	public Double getENGELS() {
		return ENGELS;
	}

	@JsonSetter("ENGELS")
	public void setENGELS(Double eNGELS) {
		ENGELS = eNGELS;
	}

	public Double getENGOSZ() {
		return ENGOSZ;
	}

	@JsonSetter("ENGOSZ")
	public void setENGOSZ(Double eNGOSZ) {
		ENGOSZ = eNGOSZ;
	}

	public Double getTENSZO() {
		return TENSZO;
	}

	@JsonSetter("TENSZO")
	public void setTENSZO(Double tENSZO) {
		TENSZO = tENSZO;
	}

	public Double getTENSZA() {
		return TENSZA;
	}

	@JsonSetter("TENSZA")
	public void setTENSZA(Double tENSZA) {
		TENSZA = tENSZA;
	}

	public Double getTENIFA() {
		return TENIFA;
	}

	@JsonSetter("TENIFA")
	public void setTENIFA(Double tENIFA) {
		TENIFA = tENIFA;
	}

	public Double getTENVEN() {
		return TENVEN;
	}

	@JsonSetter("TENVEN")
	public void setTENVEN(Double tENVEN) {
		TENVEN = tENVEN;
	}

	public Double getTENREN() {
		return TENREN;
	}

	@JsonSetter("TENREN")
	public void setTENREN(Double tENREN) {
		TENREN = tENREN;
	}

	public Double getTENSPA() {
		return TENSPA;
	}

	@JsonSetter("TENSPA")
	public void setTENSPA(Double tENSPA) {
		TENSPA = tENSPA;
	}

	public Double getTENFIT() {
		return TENFIT;
	}

	@JsonSetter("TENFIT")
	public void setTENFIT(Double tENFIT) {
		TENFIT = tENFIT;
	}

	public Double getTENEGY() {
		return TENEGY;
	}

	@JsonSetter("TENEGY")
	public void setTENEGY(Double tENEGY) {
		TENEGY = tENEGY;
	}

	public Double getTENELO() {
		return TENELO;
	}

	@JsonSetter("TENELO")
	public void setTENELO(Double tENELO) {
		TENELO = tENELO;
	}

	public Double getTENELS() {
		return TENELS;
	}

	@JsonSetter("TENELS")
	public void setTENELS(Double tENELS) {
		TENELS = tENELS;
	}

	public Double getTENOSZ() {
		return TENOSZ;
	}

	@JsonSetter("TENOSZ")
	public void setTENOSZ(Double tENOSZ) {
		TENOSZ = tENOSZ;
	}

	public Double getENNSZO() {
		return ENNSZO;
	}

	@JsonSetter("ENNSZO")
	public void setENNSZO(Double eNNSZO) {
		ENNSZO = eNNSZO;
	}

	public Double getENNSZA() {
		return ENNSZA;
	}

	@JsonSetter("ENNSZA")
	public void setENNSZA(Double eNNSZA) {
		ENNSZA = eNNSZA;
	}

	public Double getENNIFA() {
		return ENNIFA;
	}

	@JsonSetter("ENNIFA")
	public void setENNIFA(Double eNNIFA) {
		ENNIFA = eNNIFA;
	}

	public Double getENNVEN() {
		return ENNVEN;
	}

	@JsonSetter("ENNVEN")
	public void setENNVEN(Double eNNVEN) {
		ENNVEN = eNNVEN;
	}

	public Double getENNREN() {
		return ENNREN;
	}

	@JsonSetter("ENNREN")
	public void setENNREN(Double eNNREN) {
		ENNREN = eNNREN;
	}

	public Double getENNSPA() {
		return ENNSPA;
	}

	@JsonSetter("ENNSPA")
	public void setENNSPA(Double eNNSPA) {
		ENNSPA = eNNSPA;
	}

	public Double getENNFIT() {
		return ENNFIT;
	}

	@JsonSetter("ENNFIT")
	public void setENNFIT(Double eNNFIT) {
		ENNFIT = eNNFIT;
	}

	public Double getENNEGY() {
		return ENNEGY;
	}

	@JsonSetter("ENNEGY")
	public void setENNEGY(Double eNNEGY) {
		ENNEGY = eNNEGY;
	}

	public Double getENNELO() {
		return ENNELO;
	}

	@JsonSetter("ENNELO")
	public void setENNELO(Double eNNELO) {
		ENNELO = eNNELO;
	}

	public Double getENNELS() {
		return ENNELS;
	}

	@JsonSetter("ENNELS")
	public void setENNELS(Double eNNELS) {
		ENNELS = eNNELS;
	}

	public Double getENNOSZ() {
		return ENNOSZ;
	}

	@JsonSetter("ENNOSZ")
	public void setENNOSZ(Double eNNOSZ) {
		ENNOSZ = eNNOSZ;
	}

	public Double getFIZ_KP() {
		return FIZ_KP;
	}

	@JsonSetter("FIZ_KP")
	public void setFIZ_KP(Double fIZ_KP) {
		FIZ_KP = fIZ_KP;
	}

	public Double getFIZ_HK() {
		return FIZ_HK;
	}

	@JsonSetter("FIZ_HK")
	public void setFIZ_HK(Double fIZ_HK) {
		FIZ_HK = fIZ_HK;
	}

	public Double getFIZ_FS() {
		return FIZ_FS;
	}

	@JsonSetter("FIZ_FS")
	public void setFIZ_FS(Double fIZ_FS) {
		FIZ_FS = fIZ_FS;
	}

	public Double getFIZ_BS() {
		return FIZ_BS;
	}

	@JsonSetter("FIZ_BS")
	public void setFIZ_BS(Double fIZ_BS) {
		FIZ_BS = fIZ_BS;
	}

	public Double getVENFOR() {
		return VENFOR;
	}

	@JsonSetter("VENFOR")
	public void setVENFOR(Double vENFOR) {
		VENFOR = vENFOR;
	}

	public Integer getC_GENID() {
		return C_GENID;
	}

	@JsonSetter("C_GENID")
	public void setC_GENID(Integer c_GENID) {
		C_GENID = c_GENID;
	}

	public Integer getC_SOURCE() {
		return C_SOURCE;
	}

	@JsonSetter("C_SOURCE")
	public void setC_SOURCE(Integer c_SOURCE) {
		C_SOURCE = c_SOURCE;
	}

	public Integer getC_TARGET() {
		return C_TARGET;
	}

	@JsonSetter("C_TARGET")
	public void setC_TARGET(Integer c_TARGET) {
		C_TARGET = c_TARGET;
	}

	public Integer getC_CONTROL() {
		return C_CONTROL;
	}

	@JsonSetter("C_CONTROL")
	public void setC_CONTROL(Integer c_CONTROL) {
		C_CONTROL = c_CONTROL;
	}

	public Double getTERREG() {
		return TERREG;
	}

	@JsonSetter("TERREG")
	public void setTERREG(Double tERREG) {
		TERREG = tERREG;
	}

	public Double getENGREG() {
		return ENGREG;
	}

	@JsonSetter("ENGREG")
	public void setENGREG(Double eNGREG) {
		ENGREG = eNGREG;
	}

	public Double getTENREG() {
		return TENREG;
	}

	@JsonSetter("TENREG")
	public void setTENREG(Double tENREG) {
		TENREG = tENREG;
	}

	public Double getENNREG() {
		return ENNREG;
	}

	@JsonSetter("ENNREG")
	public void setENNREG(Double eNNREG) {
		ENNREG = eNNREG;
	}

	public String getSZOTIP() {
		return SZOTIP;
	}

	@JsonSetter("SZOTIP")
	public void setSZOTIP(String sZOTIP) {
		SZOTIP = sZOTIP;
	}

	public Double getTERFIT() {
		return TERFIT;
	}

	@JsonSetter("TERFIT")
	public void setTERFIT(Double tERFIT) {
		TERFIT = tERFIT;
	}

	public Double getENGFIT() {
		return ENGFIT;
	}

	@JsonSetter("ENGFIT")
	public void setENGFIT(Double eNGFIT) {
		ENGFIT = eNGFIT;
	}

}
