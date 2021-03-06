package at.irian.jsfatwork.gui.jsf;

import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

@FacesValidator(value = BirthdayValidator.VALIDATOR_ID)
public class BirthdayValidator implements Validator, Serializable {
    private static final long serialVersionUID = 7946668244924263955L;

    public static final String VALIDATOR_ID = "at.irian.Birthday";

    public void validate(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        Date date = (Date) value;
        if (date != null) {
            // Check if birthdate is after now
            if (date.after(new Date())) {
                FacesMessage msg = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Birthday is in the future.", null);
                throw new ValidatorException(msg);
            }

            // Check if birthdate is before 1.1.1900
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 1900);
            cal.set(Calendar.MONTH, 0);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            if (date.before(cal.getTime())) {
                FacesMessage msg = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Birthday is before Jan 1, 1900.", null);
                throw new ValidatorException(msg);
            }
        }
    }

}