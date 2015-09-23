package name.zemon.david.propwareide.server.file.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by david on 9/20/15.
 */
public class Project {
    private String     name;
    private Collection<String> fileNames;

    public Project() {
        this.name = "";
        this.fileNames = new ArrayList<>();
    }

    public Project(final String name, final Collection<String> fileNames) {
        this.name = name;
        this.fileNames = new ArrayList<>(fileNames);
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<String> getFileNames() {
        return Collections.unmodifiableCollection(this.fileNames);
    }

    public void setFileNames(final Collection<String> fileNames) {
        this.fileNames = fileNames;
    }

    public void addFileName (final String fileName) {
        this.fileNames.add(fileName);
    }

    public void addAllFileNames (final Collection<String> fileNames) {
        this.fileNames.addAll(fileNames);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        final Project project = (Project) o;

        return new EqualsBuilder()
                .append(this.name, project.name)
                .append(this.fileNames, project.fileNames)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.name)
                .append(this.fileNames)
                .toHashCode();
    }
}
