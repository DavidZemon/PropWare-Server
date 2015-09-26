package name.zemon.david.propwareide.common.pojo;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by david on 9/23/15.
 */
public class BuildRequest {
    @Nonnull
    private Collection<PWFile>  files;
    @Nonnull
    private Map<String, String> cmakeOptions;
    @Nonnull
    private Map<String, String> makeOptions;
    @Nonnull
    private Collection<String>  targets;

    public BuildRequest() {
        this.files = new ArrayList<>();
        this.cmakeOptions = new HashMap<>();
        this.makeOptions = new HashMap<>();
        this.targets = new ArrayList<>();
    }

    @Nonnull
    public Collection<PWFile> getFiles() {
        return Collections.unmodifiableCollection(this.files);
    }

    public void setFiles(@Nonnull final Collection<PWFile> files) {
        this.files = new ArrayList<>(files);
    }

    public void addFile(@Nonnull final PWFile file) {
        this.files.add(file);
    }

    public void addFiles(@Nonnull final Collection<PWFile> files) {
        this.files.addAll(files);
    }

    @Nonnull
    public Map<String, String> getCMakeOptions() {
        return Collections.unmodifiableMap(this.cmakeOptions);
    }

    public void setCMakeOptions(@Nonnull final Map<String, String> cmakeOptions) {
        this.cmakeOptions = new HashMap<>(cmakeOptions);
    }

    public void addCMakeOption(@Nonnull final String name, @Nonnull final String value) {
        this.cmakeOptions.put(name, value);
    }

    public void addCMakeOptions(@Nonnull final Map<String, String> options) {
        this.cmakeOptions.putAll(options);
    }

    @Nonnull
    public Map<String, String> getMakeOptions() {
        return Collections.unmodifiableMap(this.makeOptions);
    }

    public void setMakeOptions(@Nonnull final Map<String, String> makeOptions) {
        this.makeOptions = new HashMap<>(makeOptions);
    }

    public void addMakeOption(@Nonnull final String name, @Nonnull final String value) {
        this.makeOptions.put(name, value);
    }

    public void addMakeOptions(@Nonnull final Map<String, String> options) {
        this.makeOptions.putAll(options);
    }

    @Nonnull
    public Collection<String> getTargets() {
        return Collections.unmodifiableCollection(this.targets);
    }

    public void setTargets(@Nonnull final Collection<String> targets) {
        this.targets = new ArrayList<>(targets);
    }

    public void addTarget(@Nonnull final String target) {
        this.targets.add(target);
    }

    public void addTargets(@Nonnull final Collection<String> targets) {
        this.targets.addAll(targets);
    }
}
