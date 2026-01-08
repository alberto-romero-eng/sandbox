#! /bin/bash

# JAVA HELPER WM PLATFORM
# dependencies: git-bash-aliases.sh, set-java-version.sh, jh-wm-platform.sh

__JH_WMP_START_LIST_UPDATED__='2026-01-08 03:30'


### list(s)


###
### eureka list
###

_s='';

_s=${_s}'microservicesregistry'' '

_jh_eureka_list=${_s}


###
### wm list
###

_s='';

_s=${_s}'usermanagement'' '

# _s=${_s}'marketplacealiexpress'' '
# _s=${_s}'marketplaceamazon'' '
# _s=${_s}'marketplacebackmarket'' '
# _s=${_s}'marketplaceebay'' '
# _s=${_s}'marketplacejoomlavirtuemart'' '
# _s=${_s}'marketplacemakro'' '
# _s=${_s}'marketplacemirakl'' '
# _s=${_s}'marketplacemiravia'' '
# _s=${_s}'marketplaceprestashop'' '
# _s=${_s}'marketplacerefurbed'' '
# _s=${_s}'marketplaceshein'' '
# _s=${_s}'marketplacetemu'' '
# _s=${_s}'marketplaceveepee'' '

_s=${_s}'mappingmanagement'' '
_s=${_s}'ordermanagement'' '
_s=${_s}'productmanagement'' '
# _s=${_s}'ktypeprocessor'' '
_s=${_s}'bulkfileprocessor'' '
# _s=${_s}'apiprocessor'' '

# _s=${_s}'merchantlogisfashion'' '
# _s=${_s}'carrierseur'' '
# _s=${_s}'carriertipsa'' '

# _s=${_s}'template-generator'' '
# _s=${_s}'translationservice'' '
# _s=${_s}'mailing'' '
# _s=${_s}'redirectionmanager'' '
_s=${_s}'ui'' '

_jh_wm_list=${_s}


###
### pim list
###

_s='';

_s=${_s}'usermanagement'' '
_s=${_s}'marketplaceebay'' '
_s=${_s}'bulkfileprocessor'' '

_s=${_s}'keepa'' '
_s=${_s}'aecoc'' '
# _s=${_s}'icecat'' '
_s=${_s}'pim'' '

_jh_pim_list=${_s}


###
### pit list
###

_s='';

_s=${_s}'pituploadmanagement'' '
_s=${_s}'pitsectionmanagement'' '
_s=${_s}'pitresultmanagement'' '
_s=${_s}'pitmappingmanagement'' '
_s=${_s}'pitworker'' '
_s=${_s}'pitcataloguemanagement'' '
_s=${_s}'pitapi'' '

_jh_pit_list=${_s}


###
### Select one value for ${__JH_DEPLOY_MICROSERVICES__}, or create a new one.
### Note: include always ${_jh_eureka_list} for local development
###
__JH_DEPLOY_MICROSERVICES__=$(echo ${_jh_eureka_list}${_jh_wm_list})
# __JH_DEPLOY_MICROSERVICES__=$(echo ${_jh_eureka_list}${_jh_pim_list}${_jh_pit_list})
# __JH_DEPLOY_MICROSERVICES__=$(echo ${_jh_eureka_list}${_jh_pim_list})
# __JH_DEPLOY_MICROSERVICES__=$(echo ${_jh_eureka_list}${_jh_pit_list})




### function(s)

function _jh_wm_start_platform_list {

    echo -e 'start platform list, updated: '${__JH_WMP_START_LIST_UPDATED__}'\n'
    sleep 3
    
    for _microservice in ${__JH_DEPLOY_MICROSERVICES__} ; do
        ### _vn_fq_folder='__JH_WM_'$( echo -n ${_vn_microservice} | awk '{ print toupper($0) }' )'_FOLDER__'
        ### echo '${_vn_fq_folder}: '${_vn_fq_folder}' ; ${!_vn_fq_folder}: '${!_vn_fq_folder}
        ### _jh_start_service ${!_vn_fq_folder}
        _jh_start_service ${_microservice}
        sleep 20; ### TODO delete this `sleep` later; temporal measure due to over-heating (2025-05-15)
    done;

}
