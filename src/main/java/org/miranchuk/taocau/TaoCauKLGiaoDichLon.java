package org.miranchuk.taocau;

import java.util.List;

import org.miranchuk.cophieu.CoPhieu;
public class TaoCauKLGiaoDichLon extends TaoCauKLGiaoDich
{
	public String taoCauKLGiaoDich(List<CoPhieu> list, double a) 
	{
		StringBuilder tao_cau = new StringBuilder();
		
		int count=0;
		for(CoPhieu cp : list) 
		{
			if(cp.getKL_gd() >= a) 
			{
				tao_cau.append("Ngày " + cp.getNgay() + "/" + cp.getThang()).append(" ").
				append(cp.getTenCp() + " giao dịch " + cp.getKL_gd() + " triệu cổ phiếu.").
				append(System.getProperty("line.separator"));
				
				count++;
			}
		}
		
		if(count == 0)
		{
			tao_cau.append("Không có ngày nào có khối lượng giao dịch lớn hơn " + a);
		}
		
		return tao_cau.toString();
	}
}
