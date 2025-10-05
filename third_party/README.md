# Third-Party Assets

This directory tracks vendored or pinned third-party dependencies used by the bootstrap stack.

## Updating

1. Review upstream release notes.
2. Mirror the desired version into this repository (wheel, tarball, or component snapshot).
3. Update `inventory.yml` with the new version, commit hash, and license metadata.
4. Replace the corresponding license text in `LICENSES/` if it changes.
5. Run `scripts/bootstrap.sh` to ensure the offline build remains functional.

REVIEW: Mirror artifact downloads into `third_party/vendor/` when publishing releases.
