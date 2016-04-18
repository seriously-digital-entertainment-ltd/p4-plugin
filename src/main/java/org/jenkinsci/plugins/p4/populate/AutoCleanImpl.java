package org.jenkinsci.plugins.p4.populate;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

public class AutoCleanImpl extends Populate {

	private static final long serialVersionUID = 1L;

	// usess replace, delete

	@DataBoundConstructor
    public AutoCleanImpl(boolean have, boolean force, boolean modtime, boolean quiet, String pin, ParallelSync parallel,
                         boolean replace, boolean delete, boolean revert) {
		// normal sync; no -f, no -p
		super(true, false, modtime, quiet, pin, parallel,
			replace, delete, false);
	}

	// Default for test cases
	public AutoCleanImpl() {
		super(true, true, false, false, null, null,
			false, false, false);
	}

	@Extension
	public static final class DescriptorImpl extends PopulateDescriptor {

		@Override
		public String getDisplayName() {
			return "Auto cleanup and sync";
		}
	}
}
