package fpt.poly.app.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ValidateEx {
    private HttpSession session;

    public void clear() {
        session.invalidate();
        session = null;
    }

    public void initSession(HttpServletRequest request) {
        session = request.getSession();
    }

    public void setSession(HttpServletRequest req, String... filedName) {
        for (String item : filedName) {
            session.setAttribute(item, req.getParameter(item));
        }
    }

    public HttpSession getSession() {
        return session;
    }

    public void setValue(String name, Object value) {
        session.setAttribute(name, value);
    }

    public boolean checkNull(HttpServletRequest req, String[] label, String... filedName) {
        boolean check = false;
        int index = 0;
        for (String item : filedName) {
            if (req.getParameter(item).trim().isBlank()) {
                check = true;
                session.setAttribute(item + "Err", label[index] + " Không được để trống");
            }
            index++;
        }
        return check;
    }

    public boolean checkNumberInter(HttpServletRequest req, Integer moreThan, String[] label, String... filedName) {
        boolean check = false;
        int index = 0;
        for (String item : filedName) {
            if (req.getParameter(item).trim().isBlank()) {
                check = true;
                session.setAttribute(item + "Err", label[index] + " Không được để trống");
            } else {
                try {
                    int number = Integer.valueOf(req.getParameter(item).trim());
                    if (moreThan != null && number <= moreThan) {
                        check = true;
                        session.setAttribute(item + "Err", label[index] + " phải lớn hơn " + moreThan);
                    }
                } catch (Exception e) {
                    check = true;
                    session.setAttribute(item + "Err", label[index] + " là kiểu số");
                }
            }
            index++;
        }
        return check;
    }

    public boolean checkNumberFloat(HttpServletRequest req, int moreThan, String[] label, String... filedName) {
        boolean check = false;
        int index = 0;
        for (String item : filedName) {
            if (req.getParameter(item).trim().isBlank()) {
                check = true;
                session.setAttribute(item + "Err", label[index] + " Không được để trống");
            } else {
                try {
                    float number = Float.parseFloat(req.getParameter(item).trim());
                    if (number <= moreThan) {
                        check = true;
                        session.setAttribute(item + "Err", label[index] + " phải lớn hơn " + moreThan);
                    }
                } catch (Exception e) {
                    check = true;
                    session.setAttribute(item + "Err", label[index] + " là kiểu số");
                }
            }
            index++;
        }
        return check;
    }

    public void setErr(String filedName, String mess) {
        session.setAttribute(filedName + "Err", mess);
    }
}
