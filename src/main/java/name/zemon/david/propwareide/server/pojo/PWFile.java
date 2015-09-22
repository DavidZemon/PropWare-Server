package name.zemon.david.propwareide.server.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by david on 9/21/15.
 */
public class PWFile {
    private String name;
    private String content;

    public PWFile() {
        this.name = "";
        this.content = "";
    }

    public PWFile(final String name, final String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        final PWFile PWFile = (PWFile) o;

        return new EqualsBuilder()
                .append(this.name, PWFile.name)
                .append(this.content, PWFile.content)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.name)
                .append(this.content)
                .toHashCode();
    }
}
