var EDMSApp = angular.module('EDMSApp', ['ui.bootstrap']);

EDMSApp.controller('ApplicationViewController', ['$scope', '$http', '$timeout',
function ($scope, $http, $timeout) {

    var urlBase = "/onlinecasefiling/";

    // ================= INIT =================
    $scope.List = [];
    $scope.Petition = null;
    $scope.fd_id = null;

    // ================= INIT PAGE =================
    $scope.initViewPage = function () {

        var fd_id = sessionStorage.getItem("fd_id");

        if (!fd_id) {
            console.log("No fd_id found");
            return;
        }

        $scope.fd_id = fd_id;

        // Load data
        $scope.getPetDoc();
        $scope.getDocList();
    };

    // ================= GET PETITION =================
    $scope.getPetDoc = function () {

        $http.get(urlBase + 'application/getPetDoc/' + $scope.fd_id)
        .then(function (response) {

            var data = response.data;

            if (!data.modelData) return;

            var draftNo = data.modelData.rcd_draft_no;

            if (draftNo) {

                $scope.Petition = draftNo;

                //  Wait for copy → then open PDF
                copyDocument(draftNo).then(function () {

                    $timeout(function () {
                        openPDF("case_" + draftNo + ".pdf");
                    }, 300); // small delay for safety

                });
            }

        }).catch(function () {
            console.log("Error fetching petition");
        });
    };

    // ================= GET APPLICATION LIST =================
    $scope.getDocList = function () {

        $http.get(urlBase + 'application/getDocList/' + $scope.fd_id)
        .then(function (response) {

            var data = response.data;

            $scope.List = data.modelList || [];

            // Copy in background (no open)
            angular.forEach($scope.List, function (item) {
                if (item.ap_draft_no) {
                    copyApplicationFile(item.ap_draft_no + ".pdf");
                }
            });

        }).catch(function () {
            console.log("Error fetching doc list");
        });
    };

    // ================= CLICK TO OPEN =================
    $scope.showSubDocument = function (fileName) {

        if (!fileName) {
            console.log("No file selected");
            return;
        }

        // Remove .pdf if already added
        if (!fileName.endsWith(".pdf")) {
            fileName = fileName + ".pdf";
        }

        openPDF(fileName);
    };

    // ================= COPY PETITION =================
    function copyDocument(fileName) {

        return $http.get(urlBase + 'scrutiny/copyFile', {
            params: { pu_document_name: fileName + ".pdf" }
        })
        .then(function (res) {
            console.log("Petition Copied:", res.data);
        })
        .catch(function () {
            console.log("Copy failed:", fileName);
        });
    }

    // ================= COPY APPLICATION =================
    function copyApplicationFile(fileName) {

        return $http.get(urlBase + 'scrutiny/copyApplicationFileNew', {
            params: { au_document_name: fileName }
        })
        .then(function (res) {
            console.log("Application Copied:", res.data);
        })
        .catch(function () {
            console.log("Copy failed:", fileName);
        });
    }

    // ================= OPEN PDF =================
    function openPDF(file) {

        var url = urlBase + 'uploads/' + file;

        pdfjsLib.GlobalWorkerOptions.workerSrc =
            window.location.origin + '/onlinecasefiling/js/pdfjs-3.4.120/build/pdf.worker.js';

        console.log("Opening PDF:", url);

        PDFViewerApplication.open({
            url: url
        });
    }

    // ================= INIT CALL =================
    $scope.initViewPage();

}]);