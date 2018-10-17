angular.module('aws', ['ngResource', 'ui.bootstrap']).
    factory('AWSServices', function ($resource) {
        return $resource('aws');
    }).
    factory('AWSService', function ($resource) {
        return $resource('aws/:id', {id: '@id'});
    }).
    factory("EditorStatus", function () {
        var editorEnabled = {};

        var enable = function (id, fieldName) {
            this.editorEnabled = { 'id': id, 'fieldName': fieldName };
        };

        var disable = function () {
            this.editorEnabled = {};
        };

        var isEnabled = function(id, fieldName) {
            return (this.editorEnabled['id'] == id && this.editorEnabled['fieldName'] == fieldName);
        };

        return {
            isEnabled: isEnabled,
            enable: enable,
            disable: disable
        }
    });

function AwsController($scope, $modal, AWSServices, AWSService, Status) {
    function list() {
        console.log("Got into controller");
        $scope.serviceList = AWSServices.query();
        console.log($scope.serviceList)
    }

    function clone (obj) {
        return JSON.parse(JSON.stringify(obj));
    }

    function saveAWS(aws) {
        AWSServices.save(aws,
            function () {
                Status.success("AWS Service saved");
                list();
            },
            function (result) {
                Status.error("Error saving service: " + result.status);
            }
        );
    }

    $scope.addAWS = function () {
        var addModal = $modal.open({
            templateUrl: 'templates/awsForm.html',
            controller: AWSModalController,
            resolve: {
                aws: function () {
                    return {};
                },
                action: function() {
                    return 'add';
                }
            }
        });

        addModal.result.then(function (aws) {
            saveAWS(aws);
        });
    };

    $scope.updateService = function (aws) {
        var updateModal = $modal.open({
            templateUrl: 'templates/awsForm.html',
            controller: AWSModalController,
            resolve: {
                aws: function() {
                    return clone(aws);
                },
                action: function() {
                    return 'update';
                }
            }
        });

        updateModal.result.then(function (aws) {
            saveAWS(aws);
        });
    };

    $scope.deleteService = function (aws) {
        AWSService.delete({id: aws.id},
            function () {
                Status.success("AWS Service deleted");
                list();
            },
            function (result) {
                Status.error("Error deleting service: " + result.status);
            }
        );
    };

    $scope.setAWSView = function (viewName) {
        console.log("viewname is" + viewName);
        $scope.awsView = "templates/" + viewName + ".html";
    };

    $scope.init = function() {
        list();
        $scope.setAWSView("grid");
        $scope.sortField = "name";
        $scope.sortDescending = false;
    };
}

function AWSModalController($scope, $modalInstance, aws, action) {
    $scope.awsAction = action;
    $scope.aws = aws;

    $scope.ok = function () {
        $modalInstance.close($scope.aws);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};

function AWSEditorController($scope, AWSServices, Status, EditorStatus) {
    $scope.enableEditor = function (aws, fieldName) {
        $scope.newFieldValue = aws[fieldName];
        EditorStatus.enable(aws.id, fieldName);
    };

    $scope.disableEditor = function () {
        EditorStatus.disable();
    };

    $scope.isEditorEnabled = function (aws, fieldName) {
        return EditorStatus.isEnabled(aws.id, fieldName);
    };

    $scope.save = function (aws, fieldName) {
        if ($scope.newFieldValue === "") {
            return false;
        }

        aws[fieldName] = $scope.newFieldValue;

        AWSServices.save({}, aws,
            function () {
                Status.success("AWS Service saved");
                list();
            },
            function (result) {
                Status.error("Error saving service: " + result.status);
            }
        );

        $scope.disableEditor();
    };

    $scope.disableEditor();
}

angular.module('aws').
    directive('inPlaceEdit', function () {
        return {
            restrict: 'E',
            transclude: true,
            replace: true,

            scope: {
                ipeFieldName: '@fieldName',
                ipeInputType: '@inputType',
                ipeInputClass: '@inputClass',
                ipePattern: '@pattern',
                ipeModel: '=model'
            },

            template:
                '<div>' +
                    '<span ng-hide="isEditorEnabled(ipeModel, ipeFieldName)" ng-click="enableEditor(ipeModel, ipeFieldName)">' +
                        '<span ng-transclude></span>' +
                    '</span>' +
                    '<span ng-show="isEditorEnabled(ipeModel, ipeFieldName)">' +
                        '<div class="input-append">' +
                            '<input type="{{ipeInputType}}" name="{{ipeFieldName}}" class="{{ipeInputClass}}" ' +
                                'ng-required ng-pattern="{{ipePattern}}" ng-model="newFieldValue" ' +
                                'ui-keyup="{enter: \'save(ipeModel, ipeFieldName)\', esc: \'disableEditor()\'}"/>' +
                            '<div class="btn-group btn-group-xs" role="toolbar">' +
                                '<button ng-click="save(ipeModel, ipeFieldName)" type="button" class="btn"><span class="glyphicon glyphicon-ok"></span></button>' +
                                '<button ng-click="disableEditor()" type="button" class="btn"><span class="glyphicon glyphicon-remove"></span></button>' +
                            '</div>' +
                        '</div>' +
                    '</span>' +
                '</div>',

            controller: 'AWSEditorController'
        };
    });
