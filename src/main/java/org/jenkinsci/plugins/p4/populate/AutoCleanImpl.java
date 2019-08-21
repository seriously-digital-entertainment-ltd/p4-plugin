package org.jenkinsci.plugins.p4.populate;

import hudson.Extension;

import org.kohsuke.stapler.DataBoundConstructor;

public class AutoCleanImpl extends Populate {

	private static final long serialVersionUID = 2L;
	
	// usess replace, delete

	@DataBoundConstructor
	public AutoCleanImpl(boolean have, boolean force, boolean modtime,
			boolean quiet, String pin,
			boolean replace, boolean delete, boolean revert) {
		super(true, false, modtime, quiet, pin, replace, delete, false); // normal sync; no -f, no -p
	}

	@Extension
	public static final class DescriptorImpl extends PopulateDescriptor {

		@Override
		public String getDisplayName() {
			return "Auto cleanup and sync";
		}
	}
}
