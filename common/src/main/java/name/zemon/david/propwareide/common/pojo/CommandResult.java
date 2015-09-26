package name.zemon.david.propwareide.common.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by david on 9/26/15.
 */
public class CommandResult {
    private int    exitCode;
    private String output;

    public CommandResult() {
        this.output = "";
    }

    public CommandResult(final int exitCode, final String output) {
        this.exitCode = exitCode;
        this.output = output;
    }

    public int getExitCode() {
        return this.exitCode;
    }

    public void setExitCode(final int exitCode) {
        this.exitCode = exitCode;
    }

    public String getOutput() {
        return this.output;
    }

    public void setOutput(final String output) {
        this.output = output;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        final CommandResult that = (CommandResult) o;

        return new EqualsBuilder()
                .append(this.exitCode, that.exitCode)
                .append(this.output, that.output)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.exitCode)
                .append(this.output)
                .toHashCode();
    }
}
