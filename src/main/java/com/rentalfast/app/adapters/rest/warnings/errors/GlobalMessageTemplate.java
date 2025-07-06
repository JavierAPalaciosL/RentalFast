package com.rentalfast.app.adapters.rest.warnings.errors;

import java.time.LocalDateTime;

public record GlobalMessageTemplate(LocalDateTime time,
                                    String code,
                                    String title,
                                    String detail,
                                    String path,
                                    GlobalMessageTemplateErrorsFields fields
                                    ) {
}
