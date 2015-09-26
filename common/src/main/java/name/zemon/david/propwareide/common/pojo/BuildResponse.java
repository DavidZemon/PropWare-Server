package name.zemon.david.propwareide.common.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by david on 9/24/15.
 */
public class BuildResponse {
    private CommandResult cmakeResult;
    private CommandResult makeResult;
    private byte[]        binary;

    public BuildResponse() {
    }

    public BuildResponse(final CommandResult cmakeResult, final CommandResult makeResult, final byte[] binary) {
        this.cmakeResult = cmakeResult;
        this.makeResult = makeResult;
        this.binary = binary;
    }

    public CommandResult getCmakeResult() {
        return this.cmakeResult;
    }

    public void setCmakeResult(final CommandResult cmakeResult) {
        this.cmakeResult = cmakeResult;
    }

    public CommandResult getMakeResult() {
        return this.makeResult;
    }

    public void setMakeResult(final CommandResult makeResult) {
        this.makeResult = makeResult;
    }

    public byte[] getBinary() {
        return this.binary;
    }

    public void setBinary(final byte[] binary) {
        this.binary = binary;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        final BuildResponse that = (BuildResponse) o;

        return new EqualsBuilder()
                .append(this.cmakeResult, that.cmakeResult)
                .append(this.makeResult, that.makeResult)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.cmakeResult)
                .append(this.makeResult)
                .toHashCode();
    }
}
