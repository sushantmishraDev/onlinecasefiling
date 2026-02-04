<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Listing Application</title>
    <link rel="stylesheet" href="petition.css">
</head>
<body>

<div class="petition-page">

    <!-- Barcode / Case ID -->
    <div class="barcode">
        ALHC00286450
    </div>

    <h2 class="court-title">
        In the Hon'ble High Court of Judicature at Allahabad
    </h2>

    <p class="center dashed">----------</p>

    <p class="center">
        <strong>Civil Misc. Listing Application No.</strong>
        <span class="underline">${listingNo}</span> of 2026
    </p>

    <p class="center">
        On behalf of applicant [wife]
    </p>

    <p class="center"><strong>In</strong></p>

    <p class="center">
        Civil Misc. Transfer Application No. 939 of 2025<br>
        (U/s 24 of the Civil Procedure Code)<br>
        [District–Shamli]
    </p>

    <br>

    <!-- Parties -->
    <p>
        <strong>${applicantName}</strong> wife of Sri ${husbandName},<br>
        D/o Sri ${fatherName}, Residing at ${applicantAddress}.
        <span class="right-label">........Applicant [Wife]</span>
    </p>

    <br>

    <p class="center"><strong>Versus</strong></p>

    <p>
        <strong>${respondentName}</strong> son of Sri ${respondentFather},<br>
        Resident of ${respondentAddress}.
        <span class="right-label">........Opposite Party</span>
    </p>

    <br>

    <!-- To Section -->
    <p><strong>To,</strong></p>

    <p class="indent">
        The Hon'ble the Chief Justice and his other companion Judges of the
        aforesaid court.
    </p>

    <p class="indent">
        The humble application of the above named my client most respectfully
        showeth as under:
    </p>

    <br>

    <!-- Grounds -->
    <ol class="grounds">
        <li>
            That the above noted case was filed seeking transfer the record of
            Case No. ${caseNo} of ${caseYear}
            (${applicantName} Vs. ${respondentName})
            U/s ${sections}, pending in the court of learned
            Principal Judge, Family Court, Shamli at Kairana to any other Court
            of District Judgeship Meerut.
        </li>

        <li>
            That the matter was heard on ${hearingDate} and by passing an
            interim order the notices were issued to other side with direction
            to list the case on the date fixed on notice.
        </li>

        <li>
            That despite elapse of about two months period till date the matter
            has not been listed. The applicant being wife is pursuing the case
            for early disposal of the matter.
        </li>

        <li>
            That therefore the matter may be listed as early as possible by
            fixing any date.
        </li>

        <li>
            That this application is being filed on ${applicationDate} and it
            is to be taken up on ${listingDate}.
        </li>
    </ol>

</div>

</body>
</html>
