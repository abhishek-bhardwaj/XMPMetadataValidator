# XMP Metadata Extractor
An XMP (Extensible Metadata Platform) file is a text file that stores metadata, such as editing settings and other information, about a digital asset, like a raw image file, alongside the original file
This Java class provides methods to extract metadata from an XMP file. The extracted metadata is stored in a Map.

## Usage

### Class: XmpMetadataExtractor

This class consists of a static method `extractMetadata`:

#### `extractMetadata(filePath: String): Map<String, String>`

Extracts metadata from an XMP file and returns a Map containing extracted metadata.

- `filePath` : Path to the XMP file.
- Returns: A Map containing extracted metadata. Keys represent tag names and values represent tag values.
