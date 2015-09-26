package name.zemon.david.propwareide.common.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by david on 9/24/15.
 */
public class BuildResponse {
    @Nonnull
    private String cmakeOutput;
    @Nullable
    private String makeOutput;
    @Nullable
    private byte[] binary;

    public BuildResponse() {
    }

    public BuildResponse(@Nonnull final String cmakeOutput, @Nullable final String makeOutput,
                         @Nullable final byte[] binary) {
        this.cmakeOutput = cmakeOutput;
        this.makeOutput = makeOutput;
        this.binary = binary;
    }

    @Nonnull
    public String getCmakeOutput() {
        return this.cmakeOutput;
    }

    public void setCmakeOutput(@Nonnull final String cmakeOutput) {
        this.cmakeOutput = cmakeOutput;
    }

    @Nullable
    public String getMakeOutput() {
        return this.makeOutput;
    }

    public void setMakeOutput(@Nullable final String makeOutput) {
        this.makeOutput = makeOutput;
    }

    @Nullable
    public byte[] getBinary() {
        return this.binary;
    }

    public void setBinary(@Nullable final byte[] binary) {
        this.binary = binary;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        final BuildResponse that = (BuildResponse) o;

        return new EqualsBuilder()
                .append(this.cmakeOutput, that.cmakeOutput)
                .append(this.makeOutput, that.makeOutput)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.cmakeOutput)
                .append(this.makeOutput)
                .toHashCode();
    }
}
