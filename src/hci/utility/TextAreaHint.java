package hci.utility;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * TextArea에 힌트를 만드는 Class입니다 
 * TextHint를 조금 수정했습니다.
 * 
 * TextAreaHint a = new TextAreaHint(JTextArea,내용)
 * 이렇게 사용하면 됩니다
 */

public class TextAreaHint implements FocusListener, DocumentListener, PropertyChangeListener {

	private final JTextArea textarea;
	private boolean isEmpty;
	private Color hintColor;
	private Color foregroundColor;
	private final String hintText;

	public TextAreaHint(final JTextArea textarea, String hintText) {

		super();
		this.textarea = textarea;
		this.hintText = hintText;
		this.hintColor = Color.LIGHT_GRAY;
		textarea.addFocusListener(this);
		registerListeners();
		updateState();

		if (!this.textarea.hasFocus()) {
			focusLost(null);
		}
	}

	public void delete() {
		unregisterListeners();
		textarea.removeFocusListener(this);
	}


	private void registerListeners() {
		textarea.getDocument().addDocumentListener(this);
		textarea.addPropertyChangeListener("foreground", this);
	}


	private void unregisterListeners() {
		textarea.getDocument().removeDocumentListener(this);
		textarea.removePropertyChangeListener("foreground", this);
	}


	public Color getGhostColor() {
		return hintColor;
	}


	public void setGhostColor(Color hintColor) {
		this.hintColor = hintColor;
	}


	private void updateState() {
		isEmpty = textarea.getText().length() == 0;
		foregroundColor = textarea.getForeground();
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (isEmpty) {
			unregisterListeners();
			try {
				textarea.setText("");
				textarea.setForeground(foregroundColor);
			} finally {
				registerListeners();
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (isEmpty) {
			unregisterListeners();
			try {
				textarea.setText(hintText);
				textarea.setForeground(hintColor);
			} finally {
				registerListeners();
			}
		}
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		updateState();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateState();
	}


	@Override
	public void insertUpdate(DocumentEvent e) {
		updateState();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateState();
	}



}