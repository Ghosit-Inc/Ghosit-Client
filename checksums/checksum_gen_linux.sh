#!/bin/bash
set -e

# Get the directory of this script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Paths relative to the script
LIBS_DIR="$SCRIPT_DIR/../build/libs"
OUTPUT="$SCRIPT_DIR/checksums.txt"

# Remove old output
[ -f "$OUTPUT" ] && rm "$OUTPUT"

# Files to process
FILES=("ghosit_client-0.0.1.jar" "ghosit_client-0.0.1-sources.jar")

for FILE in "${FILES[@]}"; do
    FULLPATH="$LIBS_DIR/$FILE"

    # Compute hashes
    MD5=$(md5sum "$FULLPATH" | awk '{print $1}')
    SHA=$(sha256sum "$FULLPATH" | awk '{print $1}')

    # Write to output
    echo "\`$FILE\`" >> "$OUTPUT"
    echo "MD5: \`$MD5\`" >> "$OUTPUT"
    echo "SHA256: \`$SHA\`" >> "$OUTPUT"
    echo "" >> "$OUTPUT"
done

echo "Checksums generated at $OUTPUT"
