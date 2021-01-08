package taocau;

import java.util.List;

import cophieu.CoPhieu;

public class TaoCauGiamManh extends TaoCauGiam
{
	public String taoCauGiam(List<CoPhieu> list) 
	{
		String[] giam_manh={"Cuối phiên ngày "," giảm mạnh "," điểm từ ", " điểm còn "};
		StringBuilder tao_cau = new StringBuilder();
		
		for (CoPhieu cp : list)
		{
			if (cp.getBien_do_dd() >= -10 && cp.getBien_do_dd() < 0)
			{
				tao_cau.append(giam_manh[0]).append(cp.getNgay()).append("/").
							append(cp.getThang() + " ").append(cp.getTenCp()).append(giam_manh[1]).
							append(Math.abs(cp.getBien_do_dd())).append(giam_manh[2]).append(cp.getGia_tham_chieu()).
							append(giam_manh[3]).append(cp.getGia_chot()).append(" điểm.").
							append(System.getProperty("line.separator"));
			}
		}
		
		return tao_cau.toString();
	}
}
