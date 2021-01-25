package com.rumbl.rumbl_pt.enums

enum class SessionStatus(val value: Int) {
    PENDING(1),
    ACCEPTED(2),
    REJECTEDBYTRAINER(3),
    CAMCELLEDBYTRAINER(4),
    CANCELLEDBYCUSTOMER(5),
    INPROGRESS(6),
    COMPLETED(7)
}