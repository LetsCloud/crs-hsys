/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + "/pdf", produces = MediaType.APPLICATION_JSON_VALUE)
public class PdfController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PdfController.class);

	@RequestMapping(value = "/test")
	public ResponseEntity<String> getPdf(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession) {
		logger.info("PdfController().getPdf()");
		String token = "465465465465";

		// create test PDF
		try {
			logger.info("PdfController().getPdf-2()");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			logger.info("PdfController().getPdf()-3");
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
			logger.info("PdfController().getPdf()-4");
			Document doc = new Document(pdfDoc, PageSize.A4);

			fullPageTable(doc);

			doc.close();

			byte[] pdf = baos.toByteArray();
			httpSession.setAttribute(token, pdf);
			logger.info("PdfController().getPdf()-end");

		} catch (Exception e) {
			System.out.println("ReportServlet::generatePDF::Exception " + e.getMessage());
		}

		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

	private Document fullPageTable(Document doc) throws IOException {
		doc.setMargins(0, 0, 0, 0);
		

		Table table = new Table(new float[10]).useAllAvailableWidth();
		table.setMarginTop(0);
		table.setMarginBottom(0);
		// first row
		Cell cell = new Cell(1, 10).add(new Paragraph("DateRange"));
		cell.setTextAlignment(TextAlignment.CENTER);
		cell.setPadding(5);
		cell.setBackgroundColor(new DeviceRgb(140, 221, 8));
		table.addCell(cell);

		table.addCell("Calldate");
		table.addCell("Calltime");
		table.addCell("Source");
		table.addCell("DialedNo");
		table.addCell("Extension");
		table.addCell("Trunk");
		table.addCell("Duration");
		table.addCell("Calltype");
		table.addCell("Callcost");
		table.addCell("Site");

		for (int i = 0; i < 100; i++) {
			table.addCell("date" + i);
			table.addCell("time" + i);
			table.addCell("source" + i);
			table.addCell("destination" + i);
			table.addCell("extension" + i);
			table.addCell("trunk" + i);
			table.addCell("dur" + i);
			table.addCell("toc" + i);
			table.addCell("callcost" + i);
			table.addCell("Site" + i);
		}
		doc.add(table);
		return doc;
	}

	private Document columnWidthExample(Document doc) throws IOException {

		float[] columnWidths = { 1, 5, 5 };
		Table table = new Table(UnitValue.createPercentArray(columnWidths));
		PdfFont f = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		Cell cell = new Cell(1, 3).add(new Paragraph("This is a header")).setFont(f).setFontSize(13)
				.setFontColor(DeviceGray.WHITE).setBackgroundColor(DeviceGray.BLACK)
				.setTextAlignment(TextAlignment.CENTER);
		table.addHeaderCell(cell);
		for (int i = 0; i < 2; i++) {
			Cell[] headerFooter = new Cell[] {
					new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("#")),
					new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Key")),
					new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Value")) };
			for (Cell hfCell : headerFooter) {
				if (i == 0) {
					table.addHeaderCell(hfCell);
				} else {
					table.addFooterCell(hfCell);
				}
			}
		}
		for (int counter = 1; counter < 101; counter++) {
			table.addCell(
					new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(counter))));
			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("key " + counter)));
			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("value " + counter)));
		}
		doc.add(table);

		return doc;
	}

	private void example1(Document doc) throws IOException {
		Table table = new Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();
		for (int i = 0; i < 16; i++) {
			table.addCell("hi");
		}
		doc.add(table);

		// Registe Font Awesome
		PdfFont font = PdfFontFactory.createFont("static/fontawesome/webfonts/fa-regular-400.ttf",
				PdfEncodings.IDENTITY_H, true);

		// Create a text and icon
		Paragraph paragraph = new Paragraph("Hello world!");
		doc.add(paragraph);
		Paragraph paragraph2 = new Paragraph("\u010c").setFont(font);
		doc.add(paragraph2);

		PdfFont f1 = PdfFontFactory.createFont("static/fontawesome/webfonts/fa-regular-400.ttf", PdfEncodings.CP1250,
				true);
		Paragraph p1 = new Paragraph("Testing of letters \u010c,\u0106,\u0160,\u017d,\uf556").setFont(f1);
		doc.add(p1);

		PdfFont f2 = PdfFontFactory.createFont("static/fontawesome/webfonts/fa-regular-400.ttf",
				PdfEncodings.IDENTITY_H);
		Paragraph p2 = new Paragraph("Testing of letters \u010c,\u0106,\u0160,\u017d,\uf556").setFont(f2);
		doc.add(p2);

		Paragraph header = new Paragraph("Copy").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
				.setFontSize(14);
		/*
		 * for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) { float x =
		 * pdfDoc.getPage(i).getPageSize().getWidth() / 2; float y =
		 * pdfDoc.getPage(i).getPageSize().getTop() - 20;
		 * 
		 * doc.showTextAligned(header, x, y, i, TextAlignment.CENTER,
		 * VerticalAlignment.BOTTOM, 0); }
		 */
	}

	private Table createHeaderTable() {
		Cell cell;
		Table headerTable = new Table(UnitValue.createPercentArray(new float[] { 3, 6, 3 })).useAllAvailableWidth();

		cell = new Cell().add(new Paragraph("Top Left:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);

		cell = new Cell().add(new Paragraph("Top Center:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);

		cell = new Cell().add(new Paragraph("Top Right:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);

		cell = new Cell().add(new Paragraph("Medium Left:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);

		cell = new Cell().add(new Paragraph("Medium Center:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);

		cell = new Cell().add(new Paragraph("Medium Right:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);

		cell = new Cell().add(new Paragraph("Bottom Left:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);

		cell = new Cell().add(new Paragraph("Bottom Center:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);

		cell = new Cell().add(new Paragraph("Bottom Right:"));
        cell.setBorder(Border.NO_BORDER);
        headerTable.addCell(cell);
        
		return headerTable;
	}
}
