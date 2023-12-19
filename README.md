# XMP Metadata Extractor

This Java class provides methods to extract metadata from an XMP file. The extracted metadata is stored in a Map.

## Usage

### Class: XmpMetadataExtractor

This class consists of a static method `extractMetadata`:

#### `extractMetadata(filePath: String): Map<String, String>`

Extracts metadata from an XMP file and returns a Map containing extracted metadata.

- `filePath` : Path to the XMP file.
- Returns: A Map containing extracted metadata. Keys represent tag names and values represent tag values.