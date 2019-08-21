package org.jenkinsci.plugins.p4.populate;

import hudson.Extension;

import org.kohsuke.stapler.DataBoundConstructor;

public class CheckOnlyImpl extends Populate {

	private static final long serialVersionUID = 2L;
	
	/**
	 * No sync, check change only
	 * 
	 * @param pin
	 */
	@DataBoundConstructor
	public CheckOnlyImpl(boolean have, boolean force, boolean modtime,
			boolean quiet, String pin,
			boolean replace, boolean delete, boolean revert) {
		super(false, false, false, quiet, null, false, false, false);
	}

	@Extension
	public static final class DescriptorImpl extends PopulateDescriptor {

		@Override
		public String getDisplayName() {
			return "Preview check Only";
		}
	}

}
