package com.raido.rental.customtag;

import com.raido.rental.entity.Car;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import com.raido.rental.logic.ResourceName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

public class CarDescriptionTag extends TagSupport {

    private Car subject;

    public void setSubject(Car subject) {
        this.subject = subject;
    }

    @Override
    public int doStartTag() throws JspException {
        try {

            startBlock();
            Locale locale = (Locale) pageContext.getSession().getAttribute("locale");
            writeCarProperty("vc.caption.make", subject.getMake(), locale);
            writeCarProperty("vc.caption.model", subject.getModel(), locale);
            writeCarProperty("vc.caption.mileage",
                    String.valueOf(subject.getMileage()).toLowerCase(), locale);
            writeCarProperty("vc.caption.power",
                    String.valueOf(subject.getPower()).toLowerCase(), locale);
            writeCarProperty("vc.caption.fuel.type",
                    String.valueOf(subject.getFuelType()).toLowerCase(), locale);
            writeCarProperty("vc.caption.transmission",
                    String.valueOf(subject.getTransmissionType()).toLowerCase(), locale);
            writeCarProperty("vc.caption.seat.count",
                    String.valueOf(subject.getSeatCount()).toLowerCase(), locale);
            writeCarProperty("vc.caption.daily.cost",
                    String.valueOf(subject.getDailyCost()).toLowerCase(), locale);
            writeCarProperty("vc.caption.body.style",
                    String.valueOf(subject.getBodyStyle()).toLowerCase(), locale);
            writeCarProperty("vc.caption.status",
                    String.valueOf(subject.getStatus()).toLowerCase(), locale);

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            endBlock();
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }

    private void startBlock() throws IOException {
        JspWriter jspWriter = pageContext.getOut();

        jspWriter.write("<div>");
    }

    private void writeCarProperty(String caption, String value,
            Locale locale) throws IOException {
        JspWriter jspWriter = pageContext.getOut();

        jspWriter.write("<dl class=\"dl-horizontal\">");
        jspWriter.write("<dt>"
                + MessageBundle.getString(ResourceName.COMMON_CAPTIONS,
                caption, locale)
                + " </dt>");
        jspWriter.write("<dd>" + value + "</dd>");
        jspWriter.write("</dl>");
    }

    private void endBlock() throws IOException {
        JspWriter jspWriter = pageContext.getOut();
        jspWriter.write("</div>");
    }

}
