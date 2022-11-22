//
//  FilterBar.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 19.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SwiftUIChipGroup
import LivesportKit

struct FilterBarView: View {

    public var selectedFilter: SearchFilter
    public var onSelectFilter: (SearchFilter) -> Void

    var body: some View {
        ChipGroup(
            chips: [
                ChipItem(name: "all".localized, isSelected: selectedFilter == SearchFilter.all),
                ChipItem(name: "competitions".localized, isSelected: selectedFilter == SearchFilter.competitions),
                ChipItem(name: "participants".localized, isSelected: selectedFilter == SearchFilter.participants),
            ],
            width: .infinity,
            selection: .single,
            onItemSelected: { chipItem in
                switch chipItem.name {
                case "all".localized: onSelectFilter(SearchFilter.all)
                case "competitions".localized: onSelectFilter(SearchFilter.competitions)
                case "participants".localized: onSelectFilter(SearchFilter.participants)
                default: break
                }
            }
        )
    }
}

struct FilterBarView_Previews: PreviewProvider {
    static var previews: some View {
        FilterBarView(
            selectedFilter: SearchFilter.all,
            onSelectFilter: { _ in }
        )
    }
}
