public abstract class Exporter {
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        if (!supports(req)) {
            throw new IllegalArgumentException(unsupportedMessage(req));
        }
        ExportResult result = doExport(new ExportRequest(valueOrEmpty(req.title), valueOrEmpty(req.body)));
        if (result == null) {
            throw new IllegalStateException("export result cannot be null");
        }
        if (result.contentType == null || result.contentType.isEmpty()) {
            throw new IllegalStateException("content type cannot be null or empty");
        }
        if (result.bytes == null) {
            throw new IllegalStateException("bytes cannot be null");
        }
        return result;
    }

    protected boolean supports(ExportRequest req) {
        return true;
    }

    protected String unsupportedMessage(ExportRequest req) {
        return "unsupported export request";
    }

    protected abstract ExportResult doExport(ExportRequest req);

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}
