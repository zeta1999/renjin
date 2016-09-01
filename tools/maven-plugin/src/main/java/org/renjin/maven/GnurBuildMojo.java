package org.renjin.maven;

import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.renjin.packaging.PackageBuilder;
import org.renjin.packaging.PackageSource;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Builds a complete package laid out according to GNU R conventions.
 */
@ThreadSafe
@Mojo(name = "build-gnur", requiresDependencyCollection = ResolutionScope.COMPILE)
public class GnurBuildMojo extends AbstractMojo {


  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  /**
   * If true, do not fail the build if compilation fails.
   */
  @Parameter(property = "ignore.gnur.compilation.failure", defaultValue = "false")
  private boolean ignoreFailure;


  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {

    try {
      PackageSource packageSource = new PackageSource.Builder(project.getBasedir())
          .setGroupId(project.getGroupId())
          .build();
      MavenBuildContext buildContext = new MavenBuildContext(project, Collections.emptySet());
      
      PackageBuilder builder = new PackageBuilder(packageSource, buildContext);
      builder.build();

    } catch (Exception e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }
}
