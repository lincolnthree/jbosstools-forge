/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.tools.forge.ui.ext.control;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.jboss.forge.addon.ui.hints.InputType;
import org.jboss.forge.addon.ui.input.InputComponent;
import org.jboss.forge.addon.ui.util.InputComponents;
import org.jboss.forge.proxy.Proxies;
import org.jboss.tools.forge.ui.ext.wizards.ForgeWizardPage;

/**
 * Builds a control
 * 
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 * 
 */
public abstract class ControlBuilder {

	/**
	 * Builds an Eclipse {@link Control} object based on the input
	 * 
	 * @param page
	 *            TODO
	 * @param input
	 * @param container
	 *            the container this control will be placed on
	 * 
	 * @return
	 */
	public abstract Control build(final ForgeWizardPage page,
			final InputComponent<?, Object> input, final Composite container);

	/**
	 * Returns the supported type this control may produce
	 * 
	 * @return
	 */
	protected abstract Class<?> getProducedType();

	/**
	 * Returns the supported input type for this component
	 * 
	 * @return
	 */
	protected abstract InputType getSupportedInputType();

	/**
	 * Returns the subclasses of {@link InputComponent}
	 * 
	 * @return
	 */
	protected abstract Class<?>[] getSupportedInputComponentTypes();

	/**
	 * Tests if this builder may handle this specific input
	 * 
	 * @param input
	 * @return
	 */
	public boolean handles(InputComponent<?, ?> input) {
		boolean handles = false;
		for (Class<?> inputType : getSupportedInputComponentTypes()) {
			if (inputType.isInstance(input)) {
				handles = true;
				break;
			}
		}

		if (handles) {
			InputType inputTypeHint = InputComponents.getInputType(input);
			if (inputTypeHint != null && inputTypeHint != InputType.DEFAULT) {
				handles = Proxies.areEquivalent(inputTypeHint,
						getSupportedInputType());
			} else {
				// Fallback to standard type
				handles = getProducedType().isAssignableFrom(
						input.getValueType());
			}
		}

		return handles;
	}

}