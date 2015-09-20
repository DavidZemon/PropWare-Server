package name.zemon.david.propwareide.server.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by david on 9/19/15.
 */
public class StringWrapper {
    private String contents;

    public StringWrapper() {
        this.contents = "";
    }

    public StringWrapper(final String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final StringWrapper that = (StringWrapper) o;

        return new EqualsBuilder()
                .append(contents, that.contents)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(contents)
                .toHashCode();
    }
}
