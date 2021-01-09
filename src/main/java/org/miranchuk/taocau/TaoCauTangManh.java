package org.miranchuk.taocau;

import java.util.List;

import org.miranchuk.cophieu.CoPhieu;

public class TaoCauTangManh extends TaoCauTang
{
	public String taoCauTang(List<CoPhieu> list) 
	{
		StringBuilder tao_cau = new StringBuilder();
		String[] tang_manh = {"Chốt phiên giao dịch ngày "," tăng mạnh "," điểm từ "," lên "};
		
		for (CoPhieu cp : list)
		{
			if (cp.getBien_do_dd() >= 10)
			{
				tao_cau.append(tang_manh[0]).append(cp.getNgay()).append("/").
							append(cp.getThang() + " ").append(cp.getTenCp()).append(tang_manh[1]).
							append(cp.getBien_do_dd()).append(tang_manh[2]).append(cp.getGia_tham_chieu()).
							append(tang_manh[3]).append(cp.getGia_chot()).append(" điểm.").
							append(System.getProperty("line.separator"));
			}
		}
		
		return tao_cau.toString();
	}
	
	
}
