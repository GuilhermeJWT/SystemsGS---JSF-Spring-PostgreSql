package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Entidade;

@FacesConverter(forClass = Entidade.class, value = "entidadeConverter")
public class EntidadeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {

		if (codigo != null && !codigo.isEmpty()) {
			return (Entidade) HibernateUtil.getCurrenteSession().get(Entidade.class, new Long(codigo));
		}

		return codigo;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {

		if (objeto != null) {
			Entidade c = (Entidade) objeto;
			return c.getEnt_codigo() != null && c.getEnt_codigo() > 0 ? c.getEnt_codigo().toString() : null;
		}

		return null;
	}

}
