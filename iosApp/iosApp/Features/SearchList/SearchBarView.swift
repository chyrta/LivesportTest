//
//  SearchBarView.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 19.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import LivesportKit

struct SearchBarView: View {
    @Binding public var searchQuery: String
    public var onSearchClick: () -> Void
    public var onChangeQuery: (String) -> Void
    public var onClearClick: () ->Void
    
    var body: some View {
        HStack {
            SearchTextField(
                title: "enter_your_search_request".localized,
                onChangeQuery: onChangeQuery,
                onClearClick: onClearClick,
                text: $searchQuery
            )
            Spacer(minLength: 16)
            Button("search_bar_button".localized, action: onSearchClick)
        }
    }
}
