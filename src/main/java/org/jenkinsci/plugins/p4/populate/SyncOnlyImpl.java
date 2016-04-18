package org.jenkinsci.plugins.p4.populate;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

public class SyncOnlyImpl extends Populate {

	private static final long serialVersionUID = 2L;

	// uses revert

	/**
	 * Sync only (optional have update)
	 * 
	 * @param have
	 */
	@DataBoundConstructor
	public SyncOnlyImpl(boolean have, boolean force, boolean modtime,
			boolean quiet, String pin,
			boolean replace, boolean delete, boolean revert) {
		super(have, false, modtime, quiet, pin, false, false, revert);
	}

	@Extension
	public static final class DescriptorImpl extends PopulateDescriptor {

		@Override
		public String getDisplayName() {
			return "Sync only";
		}
	}
}
