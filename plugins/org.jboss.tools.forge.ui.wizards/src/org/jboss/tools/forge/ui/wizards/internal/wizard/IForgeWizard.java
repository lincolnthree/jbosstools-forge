package org.jboss.tools.forge.ui.wizards.internal.wizard;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbenchWizard;
import org.jboss.tools.forge.core.runtime.ForgeRuntime;

public interface IForgeWizard extends IWorkbenchWizard {

	void doExecute(IProgressMonitor monitor);
	void doRefresh(IProgressMonitor monitor);
	Map<Object, Object> getWizardDescriptor();
	String getStatusMessage();
	ForgeRuntime getRuntime();
}
