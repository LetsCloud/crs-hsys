/**
 * 
 */
package io.crs.hsys.shared.cnst.cube;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CR
 *
 */
public enum Measure implements Serializable {

	/**
	 * KAPACITÁS MUTATÓK. Minden kapacitás adatforrás tartalmazza.
	 */

	/**
	 * A hotel teljes szobaszáma.
	 */
	ROOMS_TOTAL,

	/**
	 * A hotel teljes ágyszáma.
	 */
	BEDS_TOTAL,

	/**
	 * Out of order szobák.
	 */
	ROOMS_OOO,

	/**
	 * Out of order ágyak.
	 */
	BEDS_OOO,

	/**
	 * Rendelkezésre álló szobák száma.
	 */
	ROOMS_AVAIL,

	/**
	 * Rendelkezésre álló ágyak száma.
	 */
	BEDS_AVAIL,

	/**
	 * TELJESÍTMÉNY-SZOBAFORGALMI MUTATÓK.
	 */

	/**
	 * Szobaéjszakák száma. TERV, TÉNY és ELŐREJELZÉS adatforrásokban.
	 */
	ROOM_NTS,

	/**
	 * Érkező szobafoglalások száma. TÉNY és ELŐREJELZÉS adatforrásokban.
	 */
	RRES_ARR,

	/**
	 * Lakó szobafoglalások száma. TÉNY és ELŐREJELZÉS adatforrásokban.
	 */
	RRES_NTS,

	/**
	 * Utazó szobafoglalások száma. TÉNY és ELŐREJELZÉS adatforrásokban.
	 */
	RRES_DEP,

	/**
	 * TELEJSÍTMÉNY-VENDÉGFORGALMI MUTATÓK.
	 */

	/**
	 * VENDÉGÉJSZAKA. TERV adatforrásban felnőtt, tini és gyerek vendégéjszakák
	 * összesen. TÉNY és ELŐREJELZÉS adatforrásokban korcsoportonkénti
	 * bontásban.
	 */
	GUEST_NTS,

	/**
	 * ÉRKEZŐ VENDÉGEK. TÉNY és ELŐREJELZÉS adatforrásokban korcsoportonkénti
	 * bontásban.
	 */
	GUEST_ARR,

	/**
	 * UTAZÓ VENDÉGEK. TÉNY és ELŐREJELZÉS adatforrásokban korcsoportonkénti
	 * bontásban.
	 */
	GUEST_DEP,

	/**
	 * TELJESÍTMÉNY-VENDÉGFORGALMI MUTATÓK KORCSOPORTONKÉNTI alábontása.
	 */

	GUEST_ADULT,

	GUEST_TEEN,

	GUEST_CHILD,

	GUEST_TOTAL,

	GUEST_INFANT,

	/**
	 * 
	 */

	GRS_REV,

	NET_REV,

	GRS_CHG,

	NET_CHG,

	GRS_DSC,

	NET_DSC,

	/**
	 * 
	 */

	DEP_ROOM,

	DEP_BRKF,

	DEP_CITY_TAX,

	DEP_VEN,

	DEP_REN,

	DEP_SPA,

	DEP_FIT,

	DEP_OTHER,

	DEP_TOTAL,

	DEP_DEPOSIT,

	/**
	 * 
	 */

	PMNT_CASH,

	PMNT_CREDIT,

	PMNT_BANK,

	PMNT_OTHER,

	PMNT_TOTAL,

	/**
	 * KALKULÁLT TELJESÍTMÉNY ADATOK 
	 */
	
	GRS_ADR,
	
	NET_ADR,
	
	
	/**
	 * FOGLALTSÁGI ADATOK.
	 */

	ROOM_OCC_FULL,

	BED_OCC_FULL,

	ROOM_OCC_OOO,

	BED_OCC_OOO,
	
	GRS_REVPAR_FULL,
	
	GRS_REVPAR_OOO,
	
	NET_REVPAR_FULL,
	
	NET_REVPAR_OOO;

	public static final EnumSet<Measure> AGE_GROUPS = EnumSet.of(GUEST_ADULT, GUEST_TEEN, GUEST_CHILD, GUEST_INFANT,
			GUEST_TOTAL);

	public static final EnumSet<Measure> DEPARTMENTS = EnumSet.of(DEP_ROOM, DEP_BRKF, DEP_CITY_TAX, DEP_VEN, DEP_REN,
			DEP_SPA, DEP_FIT, DEP_OTHER, DEP_TOTAL, DEP_DEPOSIT);

	/**
	 * FRO-TERV-KAPACITÁS mutatók
	 */
	public static final EnumSet<Measure> FRO_BDGT_CAP_MEAS = EnumSet.of(ROOMS_TOTAL, BEDS_TOTAL, ROOMS_OOO, BEDS_OOO,
			ROOMS_AVAIL, BEDS_AVAIL);

	/**
	 * FRO-TERV-TELJESÍTMÉNY mutatók
	 */
	public static final EnumSet<Measure> FRO_BDGT_PFM_MEAS = EnumSet.of(ROOM_NTS, GUEST_NTS, GRS_REV, NET_REV);

	/**
	 * 
	 */
	public static final EnumSet<Measure> FRO_BDGT_OCC_MEAS = EnumSet.of(ROOM_OCC_FULL, BED_OCC_FULL, ROOM_OCC_OOO,
			BED_OCC_OOO);

	/**
	 * FRO-TÉNY-KAPACITÁS mutatók
	 */
	public static final EnumSet<Measure> FRO_ACTL_CAP_MEAS = EnumSet.of(ROOMS_TOTAL, BEDS_TOTAL, ROOMS_OOO, BEDS_OOO,
			ROOMS_AVAIL, BEDS_AVAIL);

	/**
	 * FRO-TÉNY-TELJESÍTMÉNY mutatók
	 */
	public static final EnumSet<Measure> FRO_ACTL_PFM_MEAS = EnumSet.of(ROOM_NTS, RRES_ARR, RRES_NTS, RRES_DEP,
			GUEST_ARR, GUEST_NTS, GUEST_DEP, GRS_REV, NET_REV, GRS_CHG, NET_CHG, GRS_DSC, NET_DSC, PMNT_CASH,
			PMNT_CREDIT, PMNT_BANK, PMNT_OTHER, PMNT_TOTAL);

	/**
	 * FRO-TÉNY-FOGLALTSÁGI mutatók
	 */
	public static final EnumSet<Measure> FRO_ACTL_OCC_MEAS = EnumSet.of(ROOM_OCC_FULL, BED_OCC_FULL, ROOM_OCC_OOO,
			BED_OCC_OOO);

	/**
	 * FRO-ELŐREJELZÉS-KAPACITÁS mutatók
	 */
	public static final EnumSet<Measure> FRO_FCST_CAP_MEAS = EnumSet.of(ROOMS_TOTAL, BEDS_TOTAL, ROOMS_OOO, BEDS_OOO,
			ROOMS_AVAIL, BEDS_AVAIL);

	/**
	 * FRO-ELŐREJELZÉS-TELJESÍTMÉNY mutatók
	 */
	public static final EnumSet<Measure> FRO_FCST_PFM_MEAS = EnumSet.of(ROOM_NTS, RRES_ARR, RRES_NTS, RRES_DEP,
			GUEST_ARR, GUEST_NTS, GUEST_DEP, GRS_REV, NET_REV, GRS_CHG, NET_CHG, GRS_DSC, NET_DSC);

	/**
	 * FRO-ELŐREJELZÉS-FOGLALTSÁG mutatók
	 */
	public static final EnumSet<Measure> FRO_FCST_OCC_MEAS = EnumSet.of(ROOM_OCC_FULL, BED_OCC_FULL, ROOM_OCC_OOO,
			BED_OCC_OOO);

	/**
	 * 
	 */
	public static final Map<DataSource, EnumSet<Measure>> DS_FRO_MEAS;
	static {
		Map<DataSource, EnumSet<Measure>> temp = new HashMap<DataSource, EnumSet<Measure>>();
		temp.put(DataSource.FRO_BDGT_CAPY, FRO_BDGT_CAP_MEAS);
		temp.put(DataSource.FRO_BDGT_PERF, FRO_BDGT_PFM_MEAS);
		temp.put(DataSource.FRO_BDGT_CALC, FRO_BDGT_OCC_MEAS);
		temp.put(DataSource.FRO_ACTL_CAPY, FRO_ACTL_CAP_MEAS);
		temp.put(DataSource.FRO_ACTL_PERF, FRO_ACTL_PFM_MEAS);
		temp.put(DataSource.FRO_ACTL_CALC, FRO_ACTL_OCC_MEAS);
		temp.put(DataSource.FRO_FCST_CAPY, FRO_FCST_CAP_MEAS);
		temp.put(DataSource.FRO_FCST_PERF, FRO_FCST_PFM_MEAS);
		temp.put(DataSource.FRO_FCST_CALC, FRO_FCST_OCC_MEAS);
		DS_FRO_MEAS = Collections.unmodifiableMap(temp);
	}

}
