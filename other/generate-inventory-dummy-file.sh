#! /bin/bash

# version: 2026-10-28 05:30
# stats:
# - 100 lines, 0.12 secs
# - 100K lines, 50.0 secs
# - Thus, it is cautious that 'progression_reference' be 100K at most


function generate_inventory_dummy_file {

    echo 'generate_inventory_dummy_file -- begin'

    # parameters
    file_name=${1}
    file_lines=${2}
    progression_reference=${3}

    # initialize file: header
    echo 'sku,stock,price,stp,site,condition' > ${file_name}

    # initialize variables
    i='0'
    _r='0'

    # print var refs
    echo 'params -- file_name: '${file_name}' ; file_lines: '${file_lines}' ; progression_reference: '${progression_reference}

    while [[ ${i} -lt ${file_lines} ]] ; do

        # write line in file
        line='INVENTORY_MARKET_DUMMY_P0V'${i}',1,12.34,12.31,ES,NEW'  ### INVENTORY_RABBIT_DUMMY_...
        echo ${line} >> ${file_name}

        # print progression reference
        let '_r = i % progression_reference'
        if [[ ${_r} -eq '0' ]] ; then
            echo 'progression -- i: '${i}
        fi

        # preparations for next iteration
        let 'i++'

    done

    echo 'generate_inventory_dummy_file -- done! i: '${i}
    unset file_name file_lines progression_reference i _r

}


# variables
file_name='z_inventory_market_dummy.csv'
file_lines='0'
let 'file_lines = (100 * 1000) + 4' ### iniitally (10 * 1000) + 4
progression_reference='0'
let 'progression_reference = (10 * 1000)' ### initially (1 * 1000)

# execution
time generate_inventory_dummy_file ${file_name} ${file_lines} ${progression_reference}

# end
echo 'done!'
unset file_name file_lines progression_reference i _r
unset generate_inventory_dummy_file

