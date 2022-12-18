# Required accounts and credentials

1. Generate a GPG key pair and distribute the public key as per [this blog post](http://blog.sonatype.com/2010/01/how-to-generate-pgp-signatures-with-maven). Add the following entries to `~/.gradle/gradle.properties`:
	* signing.keyId=«key id»
	* signing.password=«key password»
	* signing.secretKeyRingFile=«path to the secure gpg keyring (not public)»
1. [Sign up](https://issues.sonatype.org/secure/Signup!default.jspa) for a Jira account @ Sonatype. Send your Jira username to someone who is already allowed to publish Geb to Sonatype so that they add a comment [this ticket](https://issues.sonatype.org/browse/OSSRH-3108) to request access rights for you. Add your Sonatype credentials to `~/.gradle/gradle.properties`:
	* sonatypeOssUsername=«Jira@Sontype username»
	* sonatypeOssPassword=«Jira@Sontype password»

# Releasing

1. Ensure that the revision you're about to promote has been successfully built on [CI](https://circleci.com/gh/geb/workflows/geb/tree/master).
1. Update the version to the required one (usually just dropping -SNAPSHOT) in `buildSrc/src/main/groovy/geb.coordinates.gradle` file.
1. Change `{geb-version}` expression used in `History` section in `140-project.adoc` to a fixed version (the one that you're about to release).
1. Commit with message "Version «number»" (don't push yet)
1. Tag commit with name "v«number»" (still don't push yet)
1. Run `./gradlew publishJarsAndManual closeAndReleaseStagingRepository`
1. Wait for the new version to [appear in Maven Central](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.gebish%22%20AND%20a%3A%22geb-core%22), this might take several hours.

# Post-release actions
1. Bump the version to a snapshot of the next planned version.
1. Remove the oldest version from `manuals.include()` call in `site.gradle` and append the newly released one.
1. Add a placeholder above the newest version in `History` section in `140-project.adoc` using `{geb-version}` expression.
1. Commit with message 'Begin version «version»'
1. Push (make sure you push the tag as well).
1. Bump Geb versions in example projects: 
	* [geb-example-gradle](https://github.com/geb/geb-example-gradle)
	* [geb-example-maven](https://github.com/geb/geb-example-maven)
1. Update issues and milestones in Github tracker:
	* Find all unresolved issues in the tracker that have the fix version set to the recently released version and bulk edit them to have the fix version set to the next version.
	* Find the recently released milestone, change the version number if it's different from the one that was released and close it.
1. Wait for the build of the next version to pass and the site including manual for the released version to be published.
1. Send an email to the mailing list, you can use [this one](http://markmail.org/message/j35koyww35lh4mxk) as a template. Please mention significant breaking changes if there are any.
