package name.zemon.david.propwareide.server.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by david on 9/20/15.
 */
public class Project {
    private String              name;
    private Map<String, String> files;

    public Project() {
        this.name = "";
        this.files = new HashMap<>();
    }

    public Project(final String name) {
        this.name = name;
        this.files = new HashMap<>();
    }

    public Project(final String name, final Map<String, String> files) {
        this.name = name;
        this.files = files;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Map<String, String> getFiles() {
        return Collections.unmodifiableMap(this.files);
    }

    public void setFiles(final Map<String, String> files) {
        this.files = files;
    }

    public void addFile(final String fileName, final String file) {
        this.files.put(fileName, file);
    }

    public void addAllFiles(final Map<String, String> files) {
        this.files.putAll(files);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        final Project project = (Project) o;

        return new EqualsBuilder()
                .append(this.name, project.name)
                .append(this.files, project.files)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.name)
                .append(this.files)
                .toHashCode();
    }
}
