/**
 * 
 */
package io.crs.hsys.shared.dto.cube;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class Perf1Dto implements Dto {

	private String dim;

	private Double occAct;

	private Double occVs;

	private Double adrAct;

	private Double adrVs;

	private Double revparAct;

	private Double revparVs;

	public Perf1Dto() {
	}

	public Perf1Dto(String dim, Double occAct, Double occVs, Double adrAct, Double adrVs, Double revparAct,
			double revparVs) {
		this();
		this.dim = dim;
		this.occAct = occAct;
		this.adrAct = adrAct;
		this.revparAct = revparAct;
		this.occVs = occVs;
		this.adrVs = adrVs;
		this.revparVs = revparVs;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public Double getOccDelta() {
		return calcPercent(occAct, occVs);
	}

	public Double getAdrDelta() {
		return calcPercent(adrAct, adrVs);
	}

	public Double getRevparDelta() {
		return calcPercent(revparAct, revparVs);
	}

	private Double calcPercent(double valueAct, double valueVs) {
		if (valueVs == 0)
			return 100d;
		return 100 * (valueAct - valueVs) / valueVs;
	}

	public Double getOccAct() {
		return occAct;
	}

	public void setOccAct(Double occAct) {
		this.occAct = occAct;
	}

	public Double getOccVs() {
		return occVs;
	}

	public void setOccVs(Double occVs) {
		this.occVs = occVs;
	}

	public Double getAdrAct() {
		return adrAct;
	}

	public void setAdrAct(Double adrAct) {
		this.adrAct = adrAct;
	}

	public Double getAdrVs() {
		return adrVs;
	}

	public void setAdrVs(Double adrVs) {
		this.adrVs = adrVs;
	}

	public Double getRevparAct() {
		return revparAct;
	}

	public void setRevparAct(Double revparAct) {
		this.revparAct = revparAct;
	}

	public Double getRevparVs() {
		return revparVs;
	}

	public void setRevparVs(Double revparVs) {
		this.revparVs = revparVs;
	}
	
}
