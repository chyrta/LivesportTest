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
                ChipItem(name: Strings.shared.getString(id: "all"), isSelected: selectedFilter == SearchFilter.all),
                ChipItem(name: Strings.shared.getString(id: "competitions"), isSelected: selectedFilter == SearchFilter.competitions),
                ChipItem(name: Strings.shared.getString(id: "participants"), isSelected: selectedFilter == SearchFilter.participants),
            ],
            width: .infinity,
            selection: .single,
            onItemSelected: { chipItem in
                switch chipItem.name {
                case Strings.shared.getString(id: "all"): onSelectFilter(SearchFilter.all)
                case Strings.shared.getString(id: "competitions"): onSelectFilter(SearchFilter.competitions)
                case Strings.shared.getString(id: "participants"): onSelectFilter(SearchFilter.participants)
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
